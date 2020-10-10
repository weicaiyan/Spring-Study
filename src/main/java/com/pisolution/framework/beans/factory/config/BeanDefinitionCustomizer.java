package com.pisolution.framework.beans.factory.config;

import com.pisolution.framework.BeanDefinition;

@FunctionalInterface
public interface BeanDefinitionCustomizer {

	/**
	 * Customize the given bean definition.
	 */
	void customize(BeanDefinition bd);
}