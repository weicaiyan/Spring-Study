package com.pisolution;

import com.pisolution.framework.AppConfig;
import com.pisolution.framework.ApplicationContext;
import com.pisolution.service.UserService;

/**
 * @ClassName Test
 * @Description
 * @Author weicai.yan
 * @Date 2020/10/9 16:59
 * @Version 1.0
 **/
public class Test {
	public static void main(String[] args) {
		// 启动,扫描,创建bean(只会创建非懒加载的单例bean)
		ApplicationContext applicationContext = new ApplicationContext(AppConfig.class);
		UserService userService = (UserService) applicationContext.getBean("userService");
		userService.test();
	}
}
