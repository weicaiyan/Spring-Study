package com.pisolution.framework.beans.factory;

import com.sun.istack.internal.Nullable;

/**
 * @ClassName ListableBeanFactory
 * @Description
 * @Author weicai.yan
 * @Date 2020/10/10 16:52
 * @Version 1.0
 **/
public interface ListableBeanFactory {

	String[] getBeanNamesForType(@Nullable Class<?> type, boolean includeNonSingletons, boolean allowEagerInit);
}
