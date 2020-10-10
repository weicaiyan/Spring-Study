package com.pisolution.framework.context.annotation;

/**
 * @ClassName AnnotationConfigRegistry
 * @Description
 * @Author weicai.yan
 * @Date 2020/10/10 13:33
 * @Version 1.0
 **/
public interface AnnotationConfigRegistry {

	/**
	 * 注册配置类
	 *
	 * @param annotatedClasses 带注释的类
	 */
	void register(Class<?>... annotatedClasses);

	/**
	 * 扫描
	 *
	 * @param basePackages 基本包路径
	 */
	void scan(String... basePackages);
}
