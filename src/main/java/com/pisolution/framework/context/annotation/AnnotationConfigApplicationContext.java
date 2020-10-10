package com.pisolution.framework.context.annotation;

import com.pisolution.framework.beans.factory.support.BeanDefinitionRegistry;
import com.pisolution.framework.context.support.GenericApplicationContext;

/**
 * @ClassName AnnotationConfigApplicationContext
 * @Description
 * @Author weicai.yan
 * @Date 2020/10/10 13:32
 * @Version 1.0
 **/
public class AnnotationConfigApplicationContext extends GenericApplicationContext implements AnnotationConfigRegistry {

	private final AnnotatedBeanDefinitionReader reader;

	public AnnotationConfigApplicationContext() {
		// 创建被注解的bean读取器
		this.reader = new AnnotatedBeanDefinitionReader(this);
	}

	@Override
	public void register(Class<?>... annotatedClasses) {
		this.reader.register(annotatedClasses);
	}

	@Override
	public void scan(String... basePackages) {

	}
}
