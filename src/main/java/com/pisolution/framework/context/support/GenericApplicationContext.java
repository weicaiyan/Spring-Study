package com.pisolution.framework.context.support;

import com.pisolution.framework.beans.factory.config.BeanDefinition;
import com.pisolution.framework.beans.factory.config.ConfigurableListableBeanFactory;
import com.pisolution.framework.beans.factory.support.BeanDefinitionRegistry;
import com.pisolution.framework.beans.factory.support.DefaultListableBeanFactory;

/**
 * @ClassName GenericApplicationContext
 * @Description
 * @Author weicai.yan
 * @Date 2020/10/10 15:18
 * @Version 1.0
 **/
public class GenericApplicationContext extends AbstractApplicationContext implements BeanDefinitionRegistry {

	private final DefaultListableBeanFactory beanFactory;

	/**
	 * Create a new GenericApplicationContext.
	 * @see #registerBeanDefinition
	 * 直接在刚开始就实例化了一个工厂,并同时准备了各种list,set,map的用于存放数据,
	 * 比如解决循环依赖的singletonObjects,singletonFactories,earlySingletonObjects等
	 */
	public GenericApplicationContext() {
		this.beanFactory = new DefaultListableBeanFactory();
	}

	public GenericApplicationContext(DefaultListableBeanFactory beanFactory) {
		this.beanFactory = beanFactory;
	}

	@Override
	public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
		//第一遍时只有一个对象root被注册了
		this.beanFactory.registerBeanDefinition(beanName, beanDefinition);
	}

	@Override
	public void registerAlias(String name, String alias) {

	}

	@Override
	public ConfigurableListableBeanFactory getBeanFactory() {
		return this.beanFactory;
	}
}
