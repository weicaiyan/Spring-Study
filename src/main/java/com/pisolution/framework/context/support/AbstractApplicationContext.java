package com.pisolution.framework.context.support;

import com.pisolution.framework.ApplicationContext;
import com.pisolution.framework.beans.factory.config.BeanFactoryPostProcessor;
import com.pisolution.framework.beans.factory.config.ConfigurableListableBeanFactory;
import com.pisolution.framework.context.ConfigurableApplicationContext;
import com.pisolution.framework.context.expression.StandardBeanExpressionResolver;
import com.pisolution.framework.core.io.DefaultResourceLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName AbstractApplicationContext
 * @Description
 * @Author weicai.yan
 * @Date 2020/10/10 15:59
 * @Version 1.0
 **/
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

	/**
	 * 加锁时使用
	 */
	private final Object startupShutdownMonitor = new Object();

	private final List<BeanFactoryPostProcessor> beanFactoryPostProcessors = new ArrayList<>();

	@Override
	public void refresh() {
		// 加锁
		synchronized (startupShutdownMonitor) {
			// 准备工作包括设置启动时间,是否激活标识位
			// 初始化属性源(property source)配置

			// 返回一个factory,因为要对工厂进行初始化
			ConfigurableListableBeanFactory beanFactory = obtainFreshBeanFactory();
			// 准备工厂
			prepareBeanFactory(beanFactory);
			// 在spring的环境中去执行已经被注册的 factory processors
			// 设置执行自定义的ProcessBeanFactory 和spring内部自己定义的
			/**
			 * 注意,大部分的类实例化都在这里完成
			 */
			invokeBeanFactoryPostProcessors(beanFactory);
		}
	}

	private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {
		//这个地方需要注意getBeanFactoryPostProcessors()是获取手动给spring的BeanFactoryPostProcessor
		//自定义并不仅仅是程序员自己写的
		//自己写的可以加companent也可以不加
		//如果加了getBeanFactoryPostProcessors()这个地方得不到，是spring自己扫描的
		//为什么得不到getBeanFactoryPostProcessors（）这个方法是直接获取一个list，
		//这个list是在AnnotationConfigApplicationContext被定义
		//所谓的自定义的就是你手动调用AnnotationConfigApplicationContext.addBeanFactoryPostProcesor();
		//执行自定义类里面的方法,源代码如:postProcessor.postProcessBeanDefinitionRegistry(registry);
		//注意,所有的bean扫描实例化都在这里完成
		PostProcessorRegistrationDelegate.invokeBeanFactoryPostProcessors(beanFactory, getBeanFactoryPostProcessors());

		// Detect a LoadTimeWeaver and prepare for weaving, if found in the meantime
		// (e.g. through an @Bean method registered by ConfigurationClassPostProcessor)
		if (beanFactory.getTempClassLoader() == null && beanFactory.containsBean(LOAD_TIME_WEAVER_BEAN_NAME)) {
			beanFactory.addBeanPostProcessor(new LoadTimeWeaverAwareProcessor(beanFactory));
			beanFactory.setTempClassLoader(new ContextTypeMatchClassLoader(beanFactory.getBeanClassLoader()));
		}
	}

	public List<BeanFactoryPostProcessor> getBeanFactoryPostProcessors() {
		return this.beanFactoryPostProcessors;
	}

	private void prepareBeanFactory(ConfigurableListableBeanFactory beanFactory) {
		// Tell the internal bean factory to use the context's class loader etc.
		beanFactory.setBeanClassLoader(getClassLoader());
		//bean表达式解释器，后面说  能够获取bean当中的属性在前台页面

		//对象与string类型的转换   <property red="dao">

		// Configure the bean factory with context callbacks.
		//添加一个后置管理器
		//ApplicationContextAwareProcessor
		// 能够在bean中获得到各种*Aware（*Aware都有其作用）
		beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));

		beanFactory.addBeanPostProcessor(new ApplicationListenerDetector(this));

		// Detect a LoadTimeWeaver and prepare for weaving, if found.

		//意思是如果自定义的Bean中没有名为"systemProperties"和"systemEnvironment"的Bean，
		// 则注册两个Bena，Key为"systemProperties"和"systemEnvironment"，Value为Map，
		// 这两个Bean就是一些系统配置和系统环境信息
		// Register default environment beans.
	}

	protected ConfigurableListableBeanFactory obtainFreshBeanFactory() {
		return getBeanFactory();
	}

	@Override
	public abstract ConfigurableListableBeanFactory getBeanFactory();
}
