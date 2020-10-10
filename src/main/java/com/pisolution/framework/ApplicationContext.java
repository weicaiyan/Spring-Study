package com.pisolution.framework;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ApplicationContext
 * @Description
 * @Author weicai.yan
 * @Date 2020/10/9 17:00
 * @Version 1.0
 **/
public class ApplicationContext {

	private Class configClass;

	private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<String, BeanDefinition>();

	// 单例池
	private Map<String, Object> singletonObjects = new HashMap<String, Object>();

	private List<BeanPostProcessor> beanPostProcessors = new ArrayList<BeanPostProcessor>();

	public ApplicationContext(Class configClass) {
		this.configClass = configClass;
		// 扫描-beanDefinition
		scan(configClass);

		// 创建非懒加载的单例bean
		createNonLazySingleton();
	}

	/**
	 * 创建非懒加载的单例bean
	 */
	private void createNonLazySingleton() {
		for (String beanName : beanDefinitionMap.keySet()) {
			BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
			if (beanDefinition.getScope().equals("singleton") && !beanDefinition.isLazy()) {
				// 单例,非懒加载
				// 创建一个bean
				Object bean = createBean(beanDefinition, beanName);
				singletonObjects.put(beanName, bean);
			}
		}
	}

	private Object createBean(BeanDefinition beanDefinition, String beanName) {
		try {
			Class beanClass = beanDefinition.getBeanClass();
			try {
				Object instance = beanClass.getDeclaredConstructor().newInstance();
				// 填充对象的属性,依赖注入
				for (Field field : beanClass.getDeclaredFields()) {
					if (field.isAnnotationPresent(Autowired.class)) {
						Object bean = getBean(field.getName());
						field.setAccessible(true);
						field.set(instance, bean);
					}
				}
				for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
					beanPostProcessor.autowired(beanDefinition);
				}
				if (instance instanceof BeanNameAware) {
					BeanNameAware beanNameAware = (BeanNameAware) instance;
					beanNameAware.setBeanName(beanName);
				}

				if (instance instanceof InitializingBean) {
					InitializingBean initializingBean = (InitializingBean) instance;
					initializingBean.afterPropertiesSet();
				}
				return instance;
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		// 对象
		return null;
	}

	private void scan(Class configClass) {
		if (configClass.isAnnotationPresent(CompomentScan.class)) {
			CompomentScan compomentScan = (CompomentScan) configClass.getAnnotation(CompomentScan.class);
			// 获取扫描路径
			String path = compomentScan.value().replace(".", "/");
			ClassLoader classLoader = ApplicationContext.class.getClassLoader();
			URL resource = classLoader.getResource(path);
			File file = new File(resource.getFile());
			for (File f : file.listFiles()) {
				String s = f.getAbsolutePath();
				if (s.endsWith(".class")) {
					s = s.substring(s.indexOf("com"), s.indexOf(".class"));
					s = s.replace("\\", ".");
					try {
						Class clazz = classLoader.loadClass(s);
						if (clazz.isAnnotationPresent(Compoment.class)) {
							// 这是一个bean
							if (BeanPostProcessor.class.isAssignableFrom(clazz)) {
								BeanPostProcessor beanPostProcessor = (BeanPostProcessor)clazz.getDeclaredConstructor().newInstance();
								beanPostProcessors.add(beanPostProcessor);
							}
							BeanDefinition beanDefinition = new BeanDefinition();
							beanDefinition.setBeanClass(clazz);
							Compoment compoment = (Compoment) clazz.getAnnotation(Compoment.class);
							// TODO 如果没有value可以自动根据类名称生成beanName
							String beanName = compoment.value();
							// 非懒加载的单例bean
							if (clazz.isAnnotationPresent(Lazy.class)) {
								// 懒加载
								beanDefinition.setLazy(true);
							}
							if (clazz.isAnnotationPresent(Scope.class)) {
								Scope scope = (Scope) clazz.getAnnotation(Scope.class);
								String value = scope.value();
								beanDefinition.setScope(value);
							} else {
								// 默认是单例的
								beanDefinition.setScope("singleton");
							}
							beanDefinitionMap.put(beanName, beanDefinition);
						}
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (InstantiationException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	public Object getBean(String beanName) {
		if (!beanDefinitionMap.containsKey(beanName)) {
			throw new NullPointerException("没有bean");
		}
		BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
		if (beanDefinition.getScope().equals("singleton")) {
			// 单例
			Object o = singletonObjects.get(beanName);
			if (o == null) {
				Object bean = createBean(beanDefinition, beanName);
				singletonObjects.put(beanName, bean);
			}
			return o;
		} else if (beanDefinition.getScope().equals("prototype")) {
			// 非单例
			return createBean(beanDefinition, beanName);
		}
		return null;
	}
}
