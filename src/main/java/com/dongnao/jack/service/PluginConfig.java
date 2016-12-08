package com.dongnao.jack.service;

import java.util.List;

public class PluginConfig {
	
	List<CacheHandler> caches;
	Boolean isCache;

	public List<CacheHandler> getCaches() {
		return caches;
	}

	public void setCaches(List<CacheHandler> caches) {
		this.caches = caches;
	}

	public Boolean getIsCache() {
		return isCache;
	}

	public void setIsCache(Boolean isCache) {
		this.isCache = isCache;
	}

	 
	
	
	
}
