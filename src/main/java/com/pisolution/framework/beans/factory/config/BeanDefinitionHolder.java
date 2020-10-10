package com.pisolution.framework.beans.factory.config;

import com.sun.istack.internal.Nullable;

/**
 * @ClassName BeanDefinitionHolder
 * @Description
 * @Author weicai.yan
 * @Date 2020/10/10 14:39
 * @Version 1.0
 **/
public class BeanDefinitionHolder {

	private final BeanDefinition beanDefinition;

	private final String beanName;

	@Nullable
	private final String[] aliases;

	public BeanDefinitionHolder(BeanDefinition beanDefinition, String beanName) {
		this(beanDefinition, beanName, null);
	}

	public BeanDefinitionHolder(BeanDefinition beanDefinition, String beanName, @Nullable String[] aliases) {
		this.beanDefinition = beanDefinition;
		this.beanName = beanName;
		this.aliases = aliases;
	}

	public BeanDefinition getBeanDefinition() {
		return beanDefinition;
	}

	public String getBeanName() {
		return beanName;
	}

	public String[] getAliases() {
		return aliases;
	}
}
