package com.pisolution.framework.core.io;

import com.pisolution.framework.util.ClassUtils;
import com.sun.istack.internal.Nullable;

/**
 * @ClassName DefaultResourceLoader
 * @Description
 * @Author weicai.yan
 * @Date 2020/10/10 16:22
 * @Version 1.0
 **/
public class DefaultResourceLoader implements ResourceLoader {

	@Nullable
	private ClassLoader classLoader;

	public DefaultResourceLoader() {
		this.classLoader = ClassUtils.getDefaultClassLoader();
	}

	public DefaultResourceLoader(@Nullable ClassLoader classLoader) {
		this.classLoader = classLoader;
	}

	@Override
	@Nullable
	public ClassLoader getClassLoader() {
		return (this.classLoader != null ? this.classLoader : ClassUtils.getDefaultClassLoader());
	}
}
