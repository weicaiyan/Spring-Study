package com.pisolution.service;

import com.pisolution.framework.*;

/**
 * @ClassName UserService
 * @Description
 * @Author weicai.yan
 * @Date 2020/10/9 16:57
 * @Version 1.0
 **/
@Compoment("userService")
@Scope("prototype")
public class UserService implements BeanNameAware, InitializingBean {

	@Autowired
	private OrderService orderService;

	private String beanName;

	public void test() {
		System.out.println(orderService);
		System.out.println(beanName);
	}

	public void setBeanName(String name) {
		this.beanName = name;
	}

	public void afterPropertiesSet() {
		System.out.println("属性验证成功");
	}
}
