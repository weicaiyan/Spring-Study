package com.pisolution.framework;

/**
 * @ClassName BeanPostProcessor
 * @Description
 * @Author weicai.yan
 * @Date 2020/10/10 10:57
 * @Version 1.0
 **/
public interface BeanPostProcessor {

	void autowired(BeanDefinition beanDefinition);
}
