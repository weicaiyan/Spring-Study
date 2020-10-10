package com.pisolution.framework;

/**
 * @ClassName BeanDefinition
 * @Description  bean的定义
 * @Author weicai.yan
 * @Date 2020/10/9 17:51
 * @Version 1.0
 **/
public class BeanDefinition {

	private String scope;

	private boolean isLazy;

	private Class beanClass;

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public boolean isLazy() {
		return isLazy;
	}

	public void setLazy(boolean lazy) {
		isLazy = lazy;
	}

	public Class getBeanClass() {
		return beanClass;
	}

	public void setBeanClass(Class beanClass) {
		this.beanClass = beanClass;
	}
}
