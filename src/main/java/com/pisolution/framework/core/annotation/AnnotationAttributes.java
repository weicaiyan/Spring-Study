package com.pisolution.framework.core.annotation;

import com.sun.istack.internal.Nullable;

import java.lang.annotation.Annotation;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName AnnotationAttributes
 * @Description
 * @Author weicai.yan
 * @Date 2020/10/10 14:58
 * @Version 1.0
 **/
public class AnnotationAttributes extends LinkedHashMap<String, Object> {

	private static final String UNKNOWN = "unknown";

	@Nullable
	private final Class<? extends Annotation> annotationType;

	final String displayName;

	public AnnotationAttributes(Map<String, Object> map) {
		super(map);
		this.annotationType = null;
		this.displayName = UNKNOWN;
	}

	@Nullable
	public static AnnotationAttributes fromMap(@Nullable Map<String, Object> map) {
		if (map == null) {
			return null;
		}
		if (map instanceof AnnotationAttributes) {
			return (AnnotationAttributes) map;
		}
		return new AnnotationAttributes(map);
	}
}
