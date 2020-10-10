package com.pisolution.framework.beans.factory.support;

import com.pisolution.framework.BeanPostProcessor;
import com.pisolution.framework.beans.factory.config.ConfigurableBeanFactory;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @ClassName AbstractBeanFactory
 * @Description
 * @Author weicai.yan
 * @Date 2020/10/10 16:31
 * @Version 1.0
 **/
public abstract class AbstractBeanFactory implements ConfigurableBeanFactory {

	private final List<BeanPostProcessor> beanPostProcessors = new CopyOnWriteArrayList<>();

	@Override
	public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
		// Remove from old position, if any
		this.beanPostProcessors.remove(beanPostProcessor);
		/*if (beanPostProcessor instanceof InstantiationAwareBeanPostProcessor) {
			this.hasInstantiationAwareBeanPostProcessors = true;
		}
		if (beanPostProcessor instanceof DestructionAwareBeanPostProcessor) {
			this.hasDestructionAwareBeanPostProcessors = true;
		}*/
		this.beanPostProcessors.add(beanPostProcessor);
	}
}
