package com.pisolution.framework.core;

import com.pisolution.framework.util.ClassUtils;
import com.sun.istack.internal.Nullable;

import java.io.Serializable;
import java.lang.reflect.Type;

/**
 * @ClassName ResolvableType
 * @Description
 * @Author weicai.yan
 * @Date 2020/10/10 16:57
 * @Version 1.0
 **/
public class ResolvableType implements Serializable {

	@Nullable
	private Class<?> resolved;

	private final Type type;

	@Nullable
	private final TypeProvider typeProvider;

	@Nullable
	private final VariableResolver variableResolver;

	@Nullable
	private final ResolvableType componentType;

	@Nullable
	private final Integer hash;

	private ResolvableType(@Nullable Class<?> clazz) {
		this.resolved = (clazz != null ? clazz : Object.class);
		this.type = this.resolved;
		this.typeProvider = null;
		this.variableResolver = null;
		this.componentType = null;
		this.hash = null;
	}

	public static ResolvableType forRawClass(@Nullable Class<?> clazz) {
		return new ResolvableType(clazz);
	}
}
