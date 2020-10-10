package com.pisolution.service;

import com.pisolution.framework.BeanDefinition;
import com.pisolution.framework.BeanPostProcessor;
import com.pisolution.framework.Compoment;

/**
 * @ClassName AutowiredAnnotationBeanPostProcessor
 * @Description
 * @Author weicai.yan
 * @Date 2020/10/10 10:59
 * @Version 1.0
 **/
@Compoment
public class AutowiredAnnotationBeanPostProcessor implements BeanPostProcessor {

	public void autowired(BeanDefinition beanDefinition) {
		System.out.println("===============");
	}
}