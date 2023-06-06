package com.rabbit.task.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2023/6/5 23:35
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ElasticJobConfig {

    String name();

    String corn();

    int shardingTotalCount() default 1;

    String shardingItemParameters() default "";

    String jobParameter() default "";

    boolean failover() default false;

    boolean misfire() default true;

    String description() default "";

    boolean overwrite() default false;

    boolean streamingProcess() default false;

    String scriptCommandLine() default "";

    boolean monitorExecution() default false;

}
