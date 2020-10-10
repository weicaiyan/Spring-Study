package com.pisolution.framework.beans.factory.config;

import com.sun.istack.internal.Nullable;

/**
 * @ClassName ConfigurableBeanFactory
 * @Description
 * @Author weicai.yan
 * @Date 2020/10/10 16:32
 * @Version 1.0
 **/
public interface ConfigurableBeanFactory {

	void setBeanClassLoader(@Nullable ClassLoader beanClassLoader);

	void setBeanExpressionResolver(@Nullable BeanExpressionResolver resolver);

	/**
	 * 添加bean后置处理器到缓存CopyOnWriteArrayList中
	 *
	 * @param beanPostProcessor bean后置处理程序
	 */
	void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
}
