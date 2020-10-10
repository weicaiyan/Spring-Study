package com.pisolution.framework.context.annotation;

import com.pisolution.framework.beans.factory.annotation.AnnotatedBeanDefinition;
import com.pisolution.framework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import com.pisolution.framework.core.annotation.AnnotationAttributes;
import com.pisolution.framework.core.type.AnnotatedTypeMetadata;
import com.sun.istack.internal.Nullable;

/**
 * @ClassName AnnotationConfigUtils
 * @Description
 * @Author weicai.yan
 * @Date 2020/10/10 14:33
 * @Version 1.0
 **/
public class AnnotationConfigUtils {

	public static void processCommonDefinitionAnnotations(AnnotatedGenericBeanDefinition adb) {
		processCommonDefinitionAnnotations(adb, adb.getMetadata());
	}

	static void processCommonDefinitionAnnotations(AnnotatedBeanDefinition abd, AnnotatedTypeMetadata metadata) {
		System.out.println(abd);
	}

	@Nullable
	static AnnotationAttributes attributesFor(AnnotatedTypeMetadata metadata, String annotationClassName) {
		return AnnotationAttributes.fromMap(metadata.getAnnotationAttributes(annotationClassName, false));
	}
}
