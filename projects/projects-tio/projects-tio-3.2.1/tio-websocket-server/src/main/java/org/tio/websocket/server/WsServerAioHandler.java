package org.tio.websocket.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.ChannelContext;
import org.tio.core.GroupContext;
import org.tio.core.Tio;
import org.tio.core.exception.AioDecodeException;
import org.tio.core.intf.Packet;
import org.tio.http.common.*;
import org.tio.server.intf.ServerAioHandler;
import org.tio.utils.hutool.StrUtil;
import org.tio.websocket.common.*;
import org.tio.websocket.common.util.BASE64Util;
import org.tio.websocket.common.util.SHA1Util;
import org.tio.websocket.server.handler.IWsMsgHandler;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author tanyaowu
 *
 */
public class WsServerAioHandler implements ServerAioHandler {
	private static Logger log = LoggerFactory.getLogger(WsServerAioHandler.class);

	private WsServerConfig wsServerConfig;

	private IWsMsgHandler wsMsgHandler;

	/**
	 * 
	 * @param wsServerConfig
	 * @param wsMsgHandler
	 */
	public WsServerAioHandler(WsServerConfig wsServerConfig, IWsMsgHandler wsMsgHandler) {
		this.wsServerConfig = wsServerConfig;
		this.wsMsgHandler = wsMsgHandler;
	}

	@Override
	public WsRequest decode(ByteBuffer buffer, int limit, int position, int readableLength, ChannelContext channelContext) throws AioDecodeException {
		WsSessionContext wsSessionContext = (WsSessionContext) channelContext.getAttribute();
		//		int initPosition = buffer.position();

		if (!wsSessionContext.isHandshaked()) {
			HttpRequest request = HttpRequestDecoder.decode(buffer, limit, position, readableLength, channelContext, null);
			if (request == null) {
				return null;
			}

			HttpResponse httpResponse = updateWebSocketProtocol(request, channelContext);
			if (httpResponse == null) {
				throw new AioDecodeException("http协议升级到websocket协议失败");
			}

			wsSessionContext.setHandshakeRequestPacket(request);
			wsSessionContext.setHandshakeResponsePacket(httpResponse);

			WsRequest wsRequestPacket = new WsRequest();
			//			wsRequestPacket.setHeaders(httpResponse.getHeaders());
			//			wsRequestPacket.setBody(httpResponse.getBody());
			wsRequestPacket.setHandShake(true);

			return wsRequestPacket;
		}

		WsRequest websocketPacket = WsServerDecoder.decode(buffer, channelContext);
		return websocketPacket;
	}

	@Override
	public ByteBuffer encode(Packet packet, GroupContext groupContext, ChannelContext channelContext) {
		WsResponse wsResponse = (WsResponse) packet;

		//握手包
		if (wsResponse.isHandShake()) {
			WsSessionContext imSessionContext = (WsSessionContext) channelContext.getAttribute();
			HttpResponse handshakeResponsePacket = imSessionContext.getHandshakeResponsePacket();
			try {
				return HttpResponseEncoder.encode(handshakeResponsePacket, groupContext, channelContext);
			} catch (UnsupportedEncodingException e) {
				log.error(e.toString(), e);
				return null;
			}
		}

		ByteBuffer byteBuffer = WsServerEncoder.encode(wsResponse, groupContext, channelContext);
		return byteBuffer;
	}

	/**
	 * @return the httpConfig
	 */
	public WsServerConfig getHttpConfig() {
		return wsServerConfig;
	}

	private WsResponse h(WsRequest websocketPacket, byte[] bytes, Opcode opcode, ChannelContext channelContext) throws Exception {
		WsResponse wsResponse = null;
		if (opcode == Opcode.TEXT) {
			if (bytes == null || bytes.length == 0) {
				Tio.remove(channelContext, "错误的websocket包，body为空");
				return null;
			}
			String text = new String(bytes, wsServerConfig.getCharset());
			Object retObj = wsMsgHandler.onText(websocketPacket, text, channelContext);
			String methodName = "onText";
			wsResponse = processRetObj(retObj, methodName, channelContext);
			return wsResponse;
		} else if (opcode == Opcode.BINARY) {
			if (bytes == null || bytes.length == 0) {
				Tio.remove(channelContext, "错误的websocket包，body为空");
				return null;
			}
			Object retObj = wsMsgHandler.onBytes(websocketPacket, bytes, channelContext);
			String methodName = "onBytes";
			wsResponse = processRetObj(retObj, methodName, channelContext);
			return wsResponse;
		} else if (opcode == Opcode.PING || opcode == Opcode.PONG) {
			log.info("收到" + opcode);
			return null;
		} else if (opcode == Opcode.CLOSE) {
			Object retObj = wsMsgHandler.onClose(websocketPacket, bytes, channelContext);
			String methodName = "onClose";
			wsResponse = processRetObj(retObj, methodName, channelContext);
			return wsResponse;
		} else {
			Tio.remove(channelContext, "错误的websocket包，错误的Opcode");
			return null;
		}
	}

	@Override
	public void handler(Packet packet, ChannelContext channelContext) throws Exception {

		WsRequest wsRequest = (WsRequest) packet;

		if (wsRequest.isHandShake()) {
			WsSessionContext wsSessionContext = (WsSessionContext) channelContext.getAttribute();
			HttpRequest request = wsSessionContext.getHandshakeRequestPacket();
			HttpResponse httpResponse = wsSessionContext.getHandshakeResponsePacket();
			HttpResponse r = wsMsgHandler.handshake(request, httpResponse, channelContext);
			if (r == null) {
				Tio.remove(channelContext, "业务层不同意握手");
				return;
			}
			wsSessionContext.setHandshakeResponsePacket(r);

			WsResponse wsResponse = new WsResponse();
			wsResponse.setHandShake(true);
			Tio.send(channelContext, wsResponse);
			wsSessionContext.setHandshaked(true);

			wsMsgHandler.onAfterHandshaked(request, httpResponse, channelContext);
			return;
		}

		WsResponse wsResponse = h(wsRequest, wsRequest.getBody(), wsRequest.getWsOpcode(), channelContext);

		if (wsResponse != null) {
			Tio.send(channelContext, wsResponse);
		}

		return;
	}

	private WsResponse processRetObj(Object obj, String methodName, ChannelContext channelContext) throws Exception {
		WsResponse wsResponse = null;
		if (obj == null) {
			return null;
		} else {
			if (obj instanceof String) {
				String str = (String) obj;
				wsResponse = WsResponse.fromText(str, wsServerConfig.getCharset());
				return wsResponse;
			} else if (obj instanceof byte[]) {
				wsResponse = WsResponse.fromBytes((byte[]) obj);
				return wsResponse;
			} else if (obj instanceof WsResponse) {
				return (WsResponse) obj;
			} else if (obj instanceof ByteBuffer) {
				byte[] bs = ((ByteBuffer) obj).array();
				wsResponse = WsResponse.fromBytes(bs);
				return wsResponse;
			} else {
				log.error("{} {}.{}()方法，只允许返回byte[]、ByteBuffer、WsResponse或null，但是程序返回了{}", channelContext, this.getClass().getName(), methodName, obj.getClass().getName());
				return null;
			}
		}
	}

	/**
	 * @param httpConfig the httpConfig to set
	 */
	public void setHttpConfig(WsServerConfig httpConfig) {
		this.wsServerConfig = httpConfig;
	}

	/**
	 * 本方法改编自baseio: https://gitee.com/generallycloud/baseio<br>
	 * 感谢开源作者的付出
	 * @param request
	 * @param channelContext
	 * @return
	 * @author tanyaowu
	 */
	public static HttpResponse updateWebSocketProtocol(HttpRequest request, ChannelContext channelContext) {
		Map<String, String> headers = request.getHeaders();

		String Sec_WebSocket_Key = headers.get(HttpConst.RequestHeaderKey.Sec_WebSocket_Key);

		if (StrUtil.isNotBlank(Sec_WebSocket_Key)) {
			String Sec_WebSocket_Key_Magic = Sec_WebSocket_Key + "258EAFA5-E914-47DA-95CA-C5AB0DC85B11";
			byte[] key_array = SHA1Util.SHA1(Sec_WebSocket_Key_Magic);
			String acceptKey = BASE64Util.byteArrayToBase64(key_array);
			HttpResponse httpResponse = new HttpResponse(request);

			httpResponse.setStatus(HttpResponseStatus.C101);

			Map<HeaderName, HeaderValue> respHeaders = new HashMap<>();
			respHeaders.put(HeaderName.Connection, HeaderValue.Connection.Upgrade);
			respHeaders.put(HeaderName.Upgrade, HeaderValue.Upgrade.WebSocket);
			respHeaders.put(HeaderName.Sec_WebSocket_Accept, HeaderValue.from(acceptKey));
			httpResponse.addHeaders(respHeaders);
			return httpResponse;
		}
		return null;
	}

}
