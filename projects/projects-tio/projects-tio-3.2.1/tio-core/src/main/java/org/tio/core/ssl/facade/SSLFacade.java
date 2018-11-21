package org.tio.core.ssl.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.ChannelContext;
import org.tio.core.ssl.SslVo;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLEngineResult;
import javax.net.ssl.SSLException;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 一个
 * @author tanyaowu
 *
 */
public class SSLFacade implements ISSLFacade {
	@SuppressWarnings("unused")
	private static final String TAG = "SSLFascade";
	private static final Logger log = LoggerFactory.getLogger(SSLFacade.class);
	
	private AtomicLong sslSeq = new AtomicLong();

	private Handshaker _handshaker;
	private IHandshakeCompletedListener _hcl;
	private final Worker _worker;
	private boolean _clientMode;
	private ChannelContext channelContext;

	public SSLFacade(ChannelContext channelContext, SSLContext context, boolean client, boolean clientAuthRequired, ITaskHandler taskHandler) {
		this.channelContext = channelContext;
		//Currently there is no support for SSL session reuse,
		// so no need to take a peerHost or port from the host application
		final String who = client ? "client" : "server";
		SSLEngine engine = makeSSLEngine(context, client, clientAuthRequired);
		Buffers buffers = new Buffers(engine.getSession(), channelContext);
		_worker = new Worker(who, engine, buffers, channelContext);
		_handshaker = new Handshaker(client, _worker, taskHandler, channelContext);
		_clientMode = client;
	}

//	private void debug(final String message, final String... args) {
//		SSLLog.debug(TAG, message, args);
//	}

	@Override
	public boolean isClientMode() {
		return _clientMode;
	}

	@Override
	public void setHandshakeCompletedListener(IHandshakeCompletedListener hcl) {
		_hcl = hcl;
		attachCompletionListener();
	}

	@Override
	public void setSSLListener(ISSLListener l) {
		_worker.setSSLListener(l);
	}

	@Override
	public void setCloseListener(ISessionClosedListener l) {
		_worker.setSessionClosedListener(l);
	}

	@Override
	public void beginHandshake() throws SSLException {
		_handshaker.begin();
	}

	@Override
	public boolean isHandshakeCompleted() {
		return (_handshaker == null) || _handshaker.isFinished();
	}

	@Override
	public void encrypt(SslVo sslVo) throws SSLException {
		long seq = sslSeq.incrementAndGet();
		
		ByteBuffer src = sslVo.getByteBuffer();
		ByteBuffer[] byteBuffers = org.tio.core.utils.ByteBufferUtils.split(src, 1024 * 8);
		if (byteBuffers == null) {
			log.info("{}, 准备, SSL加密{}, 明文:{}", channelContext, channelContext.getId() + "_" + seq, sslVo);
			SSLEngineResult result = _worker.wrap(sslVo, sslVo.getByteBuffer());
			log.info("{}, 完成, SSL加密{}, 明文:{}, 结果:{}", channelContext, channelContext.getId() + "_" + seq, sslVo, result);

		} else {
			log.info("{}, 准备, SSL加密{}, 包过大，被拆成了[{}]个包进行发送, 明文:{}", channelContext, channelContext.getId() + "_" + seq, byteBuffers.length , sslVo);
			ByteBuffer[] encryptedByteBuffers = new ByteBuffer[byteBuffers.length];
			int alllen = 0;
			for (int i = 0; i < byteBuffers.length; i++) {
				SslVo sslVo1 = new SslVo(byteBuffers[i], sslVo.getObj());
				SSLEngineResult result = _worker.wrap(sslVo1, byteBuffers[i]);
				ByteBuffer encryptedByteBuffer = sslVo1.getByteBuffer();
				encryptedByteBuffers[i] = encryptedByteBuffer;
				alllen += encryptedByteBuffer.limit();
				log.info("{}, 完成, SSL加密{}, 明文:{}, 拆包[{}]的结果:{}", channelContext, channelContext.getId() + "_" + seq, sslVo, (i + 1), result);
			}
			
			
			ByteBuffer encryptedByteBuffer = ByteBuffer.allocate(alllen);
			for (int i = 0; i < encryptedByteBuffers.length; i++) {
				encryptedByteBuffer.put(encryptedByteBuffers[i]);
			}
			encryptedByteBuffer.flip();
			sslVo.setByteBuffer(encryptedByteBuffer);
		}
	}

	@Override
	public void decrypt(ByteBuffer byteBuffer) throws SSLException {
		long seq = sslSeq.incrementAndGet();
		log.info("{}, 准备, SSL解密{}, 密文:{}", channelContext, channelContext.getId() + "_" + seq, byteBuffer);
		SSLEngineResult result = _worker.unwrap(byteBuffer);
		log.info("{}, 完成, SSL解密{}, 密文:{}, 结果:{}", channelContext, channelContext.getId() + "_" + seq, byteBuffer, result);
		_handshaker.handleUnwrapResult(result);
	}

	@Override
	public void close() {
		/* Called if we want to properly close SSL */
		_worker.close(true);
	}

	@Override
	public boolean isCloseCompleted() {
		/* Host application should only close underlying transport after
		 close_notify packet generated by wrap has been sent to peer. Use this
		 method to check if the packet has been generated
		 */
		return _worker.isCloseCompleted();
	}

	@Override
	public void terminate() {
		/* Called if peer closed connection unexpectedly */
		_worker.close(false);
	}

	/* Privates */
	private void attachCompletionListener() {
		_handshaker.addCompletedListener(new IHandshakeCompletedListener() {
			@Override
			public void onComplete() {
				//_handshaker = null;
				if (_hcl != null) {
					_hcl.onComplete();
					_hcl = null;
				}
			}
		});
	}

	private SSLEngine makeSSLEngine(SSLContext context, boolean client, boolean clientAuthRequired) {
		SSLEngine engine = context.createSSLEngine();
		engine.setUseClientMode(client);
		engine.setNeedClientAuth(clientAuthRequired);
		return engine;
	}

}
