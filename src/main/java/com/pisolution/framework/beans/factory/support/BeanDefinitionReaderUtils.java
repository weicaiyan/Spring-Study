package com.pisolution.framework.beans.factory.support;

import com.pisolution.framework.beans.factory.config.BeanDefinitionHolder;

/**
 * @ClassName BeanDefinitionReaderUtils
 * @Description
 * @Author weicai.yan
 * @Date 2020/10/10 14:38
 * @Version 1.0
 **/
public class BeanDefinitionReaderUtils {

	public static void registerBeanDefinition(
			BeanDefinitionHolder definitionHolder, BeanDefinitionRegistry registry){

		// Register bean definition under primary name.
		String beanName = definitionHolder.getBeanName();
		registry.registerBeanDefinition(beanName, definitionHolder.getBeanDefinition());

		// Register aliases for bean name, if any.
		String[] aliases = definitionHolder.getAliases();
		if (aliases != null) {
			for (String alias : aliases) {
				registry.registerAlias(beanName, alias);
			}
		}
	}
}
