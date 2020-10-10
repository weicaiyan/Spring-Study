package com.pisolution.framework.beans.factory.support;

import com.pisolution.framework.beans.factory.config.SingletonBeanRegistry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName DefaultSingletonBeanRegistry
 * @Description
 * @Author weicai.yan
 * @Date 2020/10/10 15:43
 * @Version 1.0
 **/
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

	//用于存放完全初始化好的 bean从该缓存中取出的 bean可以直接使用
	private final Map<String, Object> singletonObjects = new ConcurrentHashMap<>(256);

	@Override
	public boolean containsSingleton(String beanName) {
		return this.singletonObjects.containsKey(beanName);
	}
}
