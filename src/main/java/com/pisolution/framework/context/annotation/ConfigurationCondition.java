package com.pisolution.framework.context.annotation;

/**
 * @ClassName ConfigurationCondition
 * @Description
 * @Author weicai.yan
 * @Date 2020/10/10 13:58
 * @Version 1.0
 **/
public interface ConfigurationCondition {

	ConfigurationPhase getConfigurationPhase();

	enum ConfigurationPhase {

		// 解析配置
		PARSE_CONFIGURATION,

		// 注册bean
		REGISTER_BEAN
	}
}
