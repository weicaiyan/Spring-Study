package com.pisolution.framework.context.annotation;

import com.pisolution.framework.beans.factory.support.BeanDefinitionRegistry;
import com.pisolution.framework.core.io.ResourceLoader;
import com.pisolution.framework.core.type.AnnotatedTypeMetadata;
import com.sun.istack.internal.Nullable;
import org.omg.CORBA.Environment;

/**
 * @ClassName ConditionEvaluator
 * @Description
 * @Author weicai.yan
 * @Date 2020/10/10 13:56
 * @Version 1.0
 **/
public class ConditionEvaluator {

	public ConditionEvaluator(@Nullable BeanDefinitionRegistry registry,
							  @Nullable Environment environment, @Nullable ResourceLoader resourceLoader) {
		// TODO
	}

	public boolean shouldSkip(AnnotatedTypeMetadata metadata) {
		return shouldSkip(metadata, null);
	}

	public boolean shouldSkip(@Nullable AnnotatedTypeMetadata metadata, @Nullable ConfigurationCondition.ConfigurationPhase phase) {
		// 判断是否跳过解析
		System.out.println("判断是否跳过解析");
		return false;
	}
}
