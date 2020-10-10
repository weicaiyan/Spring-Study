package com.pisolution.framework.core.type;

import java.util.Set;

/**
 * @ClassName AnnotationMetadata
 * @Description
 * @Author weicai.yan
 * @Date 2020/10/10 14:05
 * @Version 1.0
 **/
public interface AnnotationMetadata extends AnnotatedTypeMetadata{

	Set<String> getAnnotationTypes();
}
