package com.dongnao.jack.service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

public class DaoInvocationhandler implements InvocationHandler {
	
	private Object instance;
	
	private PluginConfig pluginConfig;
	
	public DaoInvocationhandler(Object object){
		this.instance = object;
	}
	
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		// intercepterChain
		String methodName = method.getName();
		
		if(pluginConfig.getIsCache()){
			List<CacheHandler> cacheHandler = pluginConfig.getCaches();
			for (CacheHandler cache : cacheHandler) {
				
				FilterMethodName name= cache.getClass().getAnnotation(FilterMethodName.class);
				
				if(method.getName().equals(name)){
					//cache.process(args[0].toString(),method,instance);
				}
				
			}
		}else{
			method.invoke(instance, args);
		}
		
		return null;
	}

}
