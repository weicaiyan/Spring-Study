package com.pisolution.framework.context.support;

import com.pisolution.framework.beans.factory.config.BeanPostProcessor;

/**
 * @ClassName ApplicationListenerDetector
 * @Description
 * @Author weicai.yan
 * @Date 2020/10/10 16:41
 * @Version 1.0
 **/
public class ApplicationListenerDetector implements BeanPostProcessor {

	private final transient AbstractApplicationContext applicationContext;

	public ApplicationListenerDetector(AbstractApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}
}
