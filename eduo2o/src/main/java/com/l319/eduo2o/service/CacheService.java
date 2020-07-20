package com.l319.eduo2o.service;

public interface CacheService {
	/**
	 * 依据K删除
	 * 
	 * @param keyPrefix
	 */
	void removeFromCache(String keyPrefix);
}
