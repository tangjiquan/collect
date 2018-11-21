package org.tio.utils.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.utils.hutool.StrUtil;

/**
 * @author tanyaowu 
 * 2018年10月21日 下午3:45:26
 */
public abstract class AbsCache implements ICache {
	@SuppressWarnings("unused")
	private static Logger log = LoggerFactory.getLogger(AbsCache.class);

	protected String cacheName = null;

	/**
	 * 
	 * @author tanyaowu
	 */
	public AbsCache(String cacheName) {
		if (StrUtil.isBlank(cacheName)) {
			throw new RuntimeException("cacheName不允许为空");
		}
		this.setCacheName(cacheName);
	}

	/**
	 * @return the cacheName
	 */
	public String getCacheName() {
		return cacheName;
	}

	/**
	 * @param cacheName the cacheName to set
	 */
	public void setCacheName(String cacheName) {
		this.cacheName = cacheName;
	}

}
