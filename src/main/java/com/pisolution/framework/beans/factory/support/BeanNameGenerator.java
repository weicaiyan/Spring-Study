package com.pisolution.framework.beans.factory.support;

import com.pisolution.framework.beans.factory.config.BeanDefinition;

/**
 * @ClassName BeanNameGenerator
 * @Description
 * @Author weicai.yan
 * @Date 2020/10/10 14:42
 * @Version 1.0
 **/
public interface BeanNameGenerator {

	String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry);
}
