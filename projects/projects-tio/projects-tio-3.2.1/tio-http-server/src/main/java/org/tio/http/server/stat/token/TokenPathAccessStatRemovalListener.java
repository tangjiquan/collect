package org.tio.http.server.stat.token;

import com.github.benmanes.caffeine.cache.RemovalCause;
import com.github.benmanes.caffeine.cache.RemovalListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.GroupContext;

/**
 * @author tanyaowu 
 * 2017年8月21日 下午1:32:32
 */
@SuppressWarnings("rawtypes")
public class TokenPathAccessStatRemovalListener implements RemovalListener {
	@SuppressWarnings("unused")
	private static Logger log = LoggerFactory.getLogger(TokenPathAccessStatRemovalListener.class);

	private TokenPathAccessStatListener tokenPathAccessStatListener;

	private GroupContext groupContext = null;

	/**
	 * 
	 * @author: tanyaowu
	 */
	public TokenPathAccessStatRemovalListener(GroupContext groupContext, TokenPathAccessStatListener tokenPathAccessStatListener) {
		this.groupContext = groupContext;
		this.tokenPathAccessStatListener = tokenPathAccessStatListener;
	}

	//	@Override
	//	public void onRemoval(RemovalNotification notification) {
	//		String token = (String) notification.getKey();
	//		TokenAccessStat tokenAccessStat = (TokenAccessStat) notification.getValue();
	//
	//		if (tokenPathAccessStatListener != null) {
	//			tokenPathAccessStatListener.onExpired(groupContext, token, tokenAccessStat);
	//		}
	//
	//		//		log.info("token数据统计[{}]\r\n{}", token, Json.toFormatedJson(tokenAccesspathStat));
	//	}

	@Override
	public void onRemoval(Object key, Object value, RemovalCause cause) {
		String token = (String) key;
		TokenAccessStat tokenAccessStat = (TokenAccessStat) value;

		if (tokenPathAccessStatListener != null) {
			tokenPathAccessStatListener.onExpired(groupContext, token, tokenAccessStat);
		}

	}
}
