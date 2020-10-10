package com.pisolution.framework.context.annotation;

import com.pisolution.framework.beans.factory.annotation.AnnotatedBeanDefinition;
import com.pisolution.framework.beans.factory.config.BeanDefinition;
import com.pisolution.framework.beans.factory.support.BeanDefinitionRegistry;
import com.pisolution.framework.beans.factory.support.BeanNameGenerator;
import com.pisolution.framework.util.ClassUtils;
import com.pisolution.framework.util.StringUtils;
import com.sun.istack.internal.Nullable;

import java.beans.Introspector;

/**
 * @ClassName AnnotationBeanNameGenerator
 * @Description
 * @Author weicai.yan
 * @Date 2020/10/10 14:42
 * @Version 1.0
 **/
public class AnnotationBeanNameGenerator implements BeanNameGenerator {

	@Override
	public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
		if (definition instanceof AnnotatedBeanDefinition) {
			String beanName = determineBeanNameFromAnnotation((AnnotatedBeanDefinition) definition);
			if (StringUtils.hasText(beanName)) {
				// Explicit bean name found.
				return beanName;
			}
		}
		// Fallback: generate a unique default bean name.
		return buildDefaultBeanName(definition, registry);
	}

	@Nullable
	protected String determineBeanNameFromAnnotation(AnnotatedBeanDefinition annotatedDef) {
		return null;
	}

	protected String buildDefaultBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
		return buildDefaultBeanName(definition);
	}

	protected String buildDefaultBeanName(BeanDefinition definition) {
		String beanClassName = definition.getBeanClassName();
		String shortClassName = ClassUtils.getShortName(beanClassName);
		return Introspector.decapitalize(shortClassName);
	}
}
