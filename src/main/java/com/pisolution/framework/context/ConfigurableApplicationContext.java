package com.pisolution.framework.context;

import com.pisolution.framework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * @ClassName ConfigurableApplicationContext
 * @Description
 * @Author weicai.yan
 * @Date 2020/10/10 15:58
 * @Version 1.0
 **/
public interface ConfigurableApplicationContext {

	void refresh();

	ConfigurableListableBeanFactory getBeanFactory();
}
