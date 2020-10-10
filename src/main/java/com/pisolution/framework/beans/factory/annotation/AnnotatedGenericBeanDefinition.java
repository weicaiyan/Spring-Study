package com.pisolution.framework.beans.factory.annotation;

import com.pisolution.framework.beans.factory.config.BeanDefinition;
import com.pisolution.framework.core.type.AnnotationMetadata;
import com.sun.istack.internal.Nullable;

/**
 * @ClassName AnnotatedGenericBeanDefinition
 * @Description
 * @Author weicai.yan
 * @Date 2020/10/10 13:53
 * @Version 1.0
 **/
public class AnnotatedGenericBeanDefinition implements AnnotatedBeanDefinition, BeanDefinition {

	@Nullable
	private volatile Object beanClass;

	private final AnnotationMetadata metadata;

	public AnnotatedGenericBeanDefinition(Class<?> beanClass) {
		setBeanClass(beanClass);
		// TODO 暂时先null
		this.metadata = null;
	}

	public void setBeanClass(Object beanClass) {
		this.beanClass = beanClass;
	}

	@Override
	public final AnnotationMetadata getMetadata() {
		return this.metadata;
	}

	@Override
	@Nullable
	public String getBeanClassName() {
		Object beanClassObject = this.beanClass;
		if (beanClassObject instanceof Class) {
			return ((Class<?>) beanClassObject).getName();
		} else {
			return (String) beanClassObject;
		}
	}
}
