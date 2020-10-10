package com.pisolution.framework.beans.factory.support;

import com.pisolution.framework.beans.factory.config.BeanDefinition;
import com.pisolution.framework.core.AliasRegistry;

/**
 * @ClassName BeanDefinitionRegistry
 * @Description BeanDefinition注册器
 * @Author weicai.yan
 * @Date 2020/10/10 13:42
 * @Version 1.0
 **/
public interface BeanDefinitionRegistry extends AliasRegistry {

	void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
