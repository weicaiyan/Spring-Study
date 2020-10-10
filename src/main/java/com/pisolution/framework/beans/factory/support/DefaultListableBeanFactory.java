package com.pisolution.framework.beans.factory.support;

import com.pisolution.framework.beans.factory.config.*;
import com.pisolution.framework.core.ResolvableType;
import com.pisolution.framework.util.ClassUtils;
import com.sun.istack.internal.Nullable;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName DefaultListableBeanFactory
 * @Description
 * @Author weicai.yan
 * @Date 2020/10/10 15:19
 * @Version 1.0
 **/
public class DefaultListableBeanFactory extends DefaultSingletonBeanRegistry implements BeanDefinitionRegistry, ConfigurableListableBeanFactory {

	/** bean定义对象的映射，由bean名称键控 */
	private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(256);

	/** 按注册顺序排列的bean定义名称列表 */
	private volatile List<String> beanDefinitionNames = new ArrayList<>(256);

	/** 手动注册单例的名称列表，按注册顺序 */
	private volatile Set<String> manualSingletonNames = new LinkedHashSet<>(16);

	/** 缓存的bean定义名称数组，以防配置被冻结 */
	@Nullable
	private volatile String[] frozenBeanDefinitionNames;

	@Override
	public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
		// 源码很复杂,这里只选择目前用到的代码,其他情况后续补充
		System.out.println("注册BeanDefinition");
		// 放入缓存
		this.beanDefinitionMap.put(beanName, beanDefinition);
		this.beanDefinitionNames.add(beanName);
		// TODO 这里为什么删除目前我不知道
		this.manualSingletonNames.remove(beanName);
		// TODO 这里为什么添加这个目前我不知道
		this.frozenBeanDefinitionNames = null;
		if (containsSingleton(beanName)) {
			// 缓存中存在已经创建完成的beanName所对应的bean
			System.out.println("缓存中存在已经创建完成的beanName所对应的bean");
		}
	}

	@Override
	public void registerAlias(String name, String alias) {
		System.out.println("注册别名");
	}

	@Override
	public String[] getBeanNamesForType(Class<?> type, boolean includeNonSingletons, boolean allowEagerInit) {
		return doGetBeanNamesForType(ResolvableType.forRawClass(type), includeNonSingletons, allowEagerInit);
	}

	@Override
	public void setBeanClassLoader(ClassLoader beanClassLoader) {

	}

	@Override
	public void setBeanExpressionResolver(BeanExpressionResolver resolver) {

	}

	@Override
	public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {

	}

	//根据类型去factory中获取对应类的名字
	//比如在工厂初始化的时候可以根据BeanFactory去获取所有的BeanFactoryProcessor
	private String[] doGetBeanNamesForType(ResolvableType type, boolean includeNonSingletons, boolean allowEagerInit) {
		List<String> result = new ArrayList<>();

		// Check all bean definitions.
		for (String beanName : this.beanDefinitionNames) {
			// Only consider bean as eligible if the bean name
			// is not defined as alias for some other bean.
			if (!isAlias(beanName)) {
				try {
					RootBeanDefinition mbd = getMergedLocalBeanDefinition(beanName);
					// Only check bean definition if it is complete.
					if (!mbd.isAbstract() && (allowEagerInit ||
							(mbd.hasBeanClass() || !mbd.isLazyInit() || isAllowEagerClassLoading()) &&
									!requiresEagerInitForType(mbd.getFactoryBeanName()))) {
						// In case of FactoryBean, match object created by FactoryBean.
						boolean isFactoryBean = isFactoryBean(beanName, mbd);
						BeanDefinitionHolder dbd = mbd.getDecoratedDefinition();
						boolean matchFound =
								(allowEagerInit || !isFactoryBean ||
										(dbd != null && !mbd.isLazyInit()) || containsSingleton(beanName)) &&
										(includeNonSingletons ||
												(dbd != null ? mbd.isSingleton() : isSingleton(beanName))) &&
										isTypeMatch(beanName, type);
						if (!matchFound && isFactoryBean) {
							// In case of FactoryBean, try to match FactoryBean instance itself next.
							beanName = FACTORY_BEAN_PREFIX + beanName;
							matchFound = (includeNonSingletons || mbd.isSingleton()) && isTypeMatch(beanName, type);
						}
						if (matchFound) {
							result.add(beanName);
						}
					}
				}
				catch (CannotLoadBeanClassException ex) {
					if (allowEagerInit) {
						throw ex;
					}
					// Probably a class name with a placeholder: let's ignore it for type matching purposes.
					if (logger.isDebugEnabled()) {
						logger.debug("Ignoring bean class loading failure for bean '" + beanName + "'", ex);
					}
					onSuppressedException(ex);
				}
				catch (BeanDefinitionStoreException ex) {
					if (allowEagerInit) {
						throw ex;
					}
					// Probably some metadata with a placeholder: let's ignore it for type matching purposes.
					if (logger.isDebugEnabled()) {
						logger.debug("Ignoring unresolvable metadata in bean definition '" + beanName + "'", ex);
					}
					onSuppressedException(ex);
				}
			}
		}

		// Check manually registered singletons too.
		for (String beanName : this.manualSingletonNames) {
			try {
				// In case of FactoryBean, match object created by FactoryBean.
				if (isFactoryBean(beanName)) {
					if ((includeNonSingletons || isSingleton(beanName)) && isTypeMatch(beanName, type)) {
						result.add(beanName);
						// Match found for this bean: do not match FactoryBean itself anymore.
						continue;
					}
					// In case of FactoryBean, try to match FactoryBean itself next.
					beanName = FACTORY_BEAN_PREFIX + beanName;
				}
				// Match raw bean instance (might be raw FactoryBean).
				if (isTypeMatch(beanName, type)) {
					result.add(beanName);
				}
			}
			catch (NoSuchBeanDefinitionException ex) {
				// Shouldn't happen - probably a result of circular reference resolution...
				if (logger.isDebugEnabled()) {
					logger.debug("Failed to check manually registered singleton with name '" + beanName + "'", ex);
				}
			}
		}

		return StringUtils.toStringArray(result);
	}
}
