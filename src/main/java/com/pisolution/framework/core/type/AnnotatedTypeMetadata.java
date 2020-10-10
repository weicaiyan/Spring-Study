package com.pisolution.framework.core.type;

import com.sun.istack.internal.Nullable;

import java.util.Map;

/**
 *@ClassName AnnotatedTypeMetadata
 *@Description
 *@Author weicai.yan
 *@Date 2020/10/10 13:57
 *@Version 1.0
**/
public interface AnnotatedTypeMetadata {

	@Nullable
	Map<String, Object> getAnnotationAttributes(String annotationName, boolean classValuesAsString);
}
