package com.pisolution.framework.core.io;

import com.sun.istack.internal.Nullable;

/**
 * @ClassName ResourceLoader
 * @Description
 * @Author weicai.yan
 * @Date 2020/10/10 14:27
 * @Version 1.0
 **/
public interface ResourceLoader {

	@Nullable
	ClassLoader getClassLoader();
}
