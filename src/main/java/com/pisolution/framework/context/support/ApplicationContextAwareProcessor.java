package com.pisolution.framework.context.support;

import com.pisolution.framework.beans.factory.config.BeanPostProcessor;
import com.pisolution.framework.context.ConfigurableApplicationContext;

/**
 * @ClassName ApplicationContextAwareProcessor
 * @Description
 * @Author weicai.yan
 * @Date 2020/10/10 16:38
 * @Version 1.0
 **/
public class ApplicationContextAwareProcessor implements BeanPostProcessor {

	private final ConfigurableApplicationContext applicationContext;

	public ApplicationContextAwareProcessor(ConfigurableApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}
}
