package com.pisolution.framework.beans.factory.config;

/**
 * @ClassName BeanFactoryPostProcessor
 * @Description
 * @Author weicai.yan
 * @Date 2020/10/10 16:45
 * @Version 1.0
 **/
public interface BeanFactoryPostProcessor {

	void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory);
}
