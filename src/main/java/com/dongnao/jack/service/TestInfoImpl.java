package com.dongnao.jack.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.dongnao.jack.dao.CommonMapper;

import redis.clients.jedis.JedisCluster;

public class TestInfoImpl extends AbtractBaseService implements TestIntf {

	@Autowired
	JedisCluster jedisCluster;
	
	@Autowired
	CommonMapper commonMapper;
	
	public void todo(String param) {
		getProxy(CommonMapper.class,commonMapper);
	}
	
}
