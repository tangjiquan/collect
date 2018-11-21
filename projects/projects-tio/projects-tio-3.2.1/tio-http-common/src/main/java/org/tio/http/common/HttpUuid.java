package org.tio.http.common;

import org.tio.core.intf.TioUuid;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author tanyaowu
 * 2017年6月5日 上午10:44:26
 */
public class HttpUuid implements TioUuid {
	//	private static Logger log = LoggerFactory.getLogger(HttpUuid.class);

	private static AtomicLong seq = new AtomicLong();

	/**
	 *
	 * @author tanyaowu
	 */
	public HttpUuid() {
	}

	/**
	 * @return
	 * @author tanyaowu
	 */
	@Override
	public String uuid() {
		return seq.incrementAndGet() + "";
	}
}
