package com.pisolution.test;

import com.pisolution.framework.context.annotation.AnnotationConfigApplicationContext;
import com.pisolution.test.config.AppConfig;

/**
 * @ClassName SpringTest
 * @Description
 * @Author weicai.yan
 * @Date 2020/10/10 14:20
 * @Version 1.0
 **/
public class SpringTest {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
		// 这里只注册了AppConfig
		annotationConfigApplicationContext.register(AppConfig.class);
		// 分析具体注解并实例化
		annotationConfigApplicationContext.refresh();
	}
}
