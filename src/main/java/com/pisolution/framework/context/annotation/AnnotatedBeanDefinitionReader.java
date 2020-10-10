package com.pisolution.framework.context.annotation;

import com.pisolution.framework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import com.pisolution.framework.beans.factory.config.BeanDefinitionCustomizer;
import com.pisolution.framework.beans.factory.config.BeanDefinitionHolder;
import com.pisolution.framework.beans.factory.support.BeanDefinitionReaderUtils;
import com.pisolution.framework.beans.factory.support.BeanDefinitionRegistry;
import com.pisolution.framework.beans.factory.support.BeanNameGenerator;
import com.sun.istack.internal.Nullable;
import org.omg.CORBA.Environment;

import java.lang.annotation.Annotation;
import java.util.function.Supplier;

/**
 * @ClassName AnnotatedBeanDefinitionReader
 * @Description 读取被加了注释的bean
 * @Author weicai.yan
 * @Date 2020/10/10 13:39
 * @Version 1.0
 **/
public class AnnotatedBeanDefinitionReader {

	private final BeanDefinitionRegistry registry;

	private ConditionEvaluator conditionEvaluator;

	private BeanNameGenerator beanNameGenerator = new AnnotationBeanNameGenerator();

	/**
	 *  这里的BeanDefinitionRegistry registry是通过在AnnotationConfigApplicationContext
	 *  的构造方法中传进来的this
	 *  由此说明AnnotationConfigApplicationContext是一个BeanDefinitionRegistry类型的类
	 *  何以证明我们可以看到AnnotationConfigApplicationContext的类关系：
	 *  GenericApplicationContext extends AbstractApplicationContext implements BeanDefinitionRegistry
	 *  看到他实现了BeanDefinitionRegistry证明上面的说法，那么BeanDefinitionRegistry的作用是什么呢？
	 *  BeanDefinitionRegistry 顾名思义就是BeanDefinition的注册器
	 *  那么何为BeanDefinition呢？参考BeanDefinition的源码的注释
	 * @param registry
	 */
	public AnnotatedBeanDefinitionReader(BeanDefinitionRegistry registry) {
		this(registry, null);
	}

	public AnnotatedBeanDefinitionReader(BeanDefinitionRegistry registry, Environment environment) {
		//获取并验证信息
		this.registry = registry;
		/**
		 * 里面有registry benfactory environment(里面存放环境信息,如计算机的用户等) classLoader等
		 */
		this.conditionEvaluator = new ConditionEvaluator(registry, environment, null);
	}

	/**
	 * 注册含有配置注解的类
	 *
	 * @param annotatedClasses 带注释的类
	 */
	public void register(Class<?>... annotatedClasses) {
		// 多个,遍历
		for (Class<?> annotatedClass : annotatedClasses) {
			registerBean(annotatedClass);
		}
	}

	/**
	 * 注册bean
	 *
	 * @param annotatedClass 带注释的class
	 */
	public void registerBean(Class<?> annotatedClass) {
		doRegisterBean(annotatedClass, null, null, null);
	}

	/**
	 * 去注册bean
	 *
	 * @param annotatedClass        带注释的类
	 * @param instanceSupplier      实例的供应商
	 * @param name                  的名字
	 * @param qualifiers            限定符
	 * @param definitionCustomizers 定义定制的
	 */
	<T> void doRegisterBean(Class<?> annotatedClass, @Nullable Supplier<T> instanceSupplier, @Nullable String name,
								@Nullable Class<? extends Annotation>[] qualifiers, BeanDefinitionCustomizer... definitionCustomizers) {
		AnnotatedGenericBeanDefinition abd = new AnnotatedGenericBeanDefinition(annotatedClass);
		// 判断是否跳过解析
		if (this.conditionEvaluator.shouldSkip(abd.getMetadata())) {
			return;
		}
		// 得到类的作用域

		// 把类的作用域添加到数据结构中

		// 生成类的名字通过beanNameGenerator
		String beanName = (name != null ? name : this.beanNameGenerator.generateBeanName(abd, this.registry));
		// 处理类中的通用注解,Lazy DependsOn Primary Role等等注解
		AnnotationConfigUtils.processCommonDefinitionAnnotations(abd);

		/**
		 * 这个BeanDefinitionHolder也是一个数据结构
		 */
		BeanDefinitionHolder definitionHolder = new BeanDefinitionHolder(abd, beanName);

		BeanDefinitionReaderUtils.registerBeanDefinition(definitionHolder, this.registry);
	}
}
