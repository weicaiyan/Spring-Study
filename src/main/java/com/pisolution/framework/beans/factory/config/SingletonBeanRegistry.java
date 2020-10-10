package com.pisolution.framework.beans.factory.config;

/**
 * @ClassName SingletonBeanRegistry
 * @Description
 * @Author weicai.yan
 * @Date 2020/10/10 15:45
 * @Version 1.0
 **/
public interface SingletonBeanRegistry {

	/**
	 * 判断缓存中是否已经存在beanName所对应的bean
	 *
	 * @param beanName bean的名字
	 * @return boolean
	 */
	boolean containsSingleton(String beanName);
}
