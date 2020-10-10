package com.pisolution.framework.beans.factory.support;

import com.pisolution.framework.beans.factory.config.BeanFactoryPostProcessor;

/**
 * @ClassName BeanDefinitionRegistryPostProcessor
 * @Description
 * @Author weicai.yan
 * @Date 2020/10/10 16:48
 * @Version 1.0
 **/
public interface BeanDefinitionRegistryPostProcessor extends BeanFactoryPostProcessor {

	void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry);
}
