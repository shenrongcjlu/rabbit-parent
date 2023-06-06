package com.rabbit.task.annotation;

import com.rabbit.task.autoconfigure.JobParserAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2023/6/5 23:33
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(JobParserAutoConfiguration.class)
public @interface EnableElasticJob {
}
