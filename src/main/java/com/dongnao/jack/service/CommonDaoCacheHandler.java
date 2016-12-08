package com.dongnao.jack.service;

import redis.clients.jedis.JedisCluster;

@FilterMethodName("value=")
public class CommonDaoCacheHandler implements CacheHandler{

	public Object process(Object object) {
		
		
		JedisCluster jedisCluster = (JedisCluster) ApplicationContextUtil.getApplicationContext().getBean("jedisCluster");
		
		return null;
	}
	
}
