package com.dongnao.jack.service;

import java.lang.reflect.Proxy;

public abstract class AbtractBaseService {
	public  Object getProxy(Class<?>  intfClass,Object mapperInstance){
		return Proxy.newProxyInstance(intfClass.getClassLoader(), new Class<?>[]{intfClass}, new DaoInvocationhandler(mapperInstance));
	}
}
