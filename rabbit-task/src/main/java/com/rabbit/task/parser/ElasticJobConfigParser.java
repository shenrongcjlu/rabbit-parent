package com.rabbit.task.parser;

import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import com.rabbit.task.annotation.ElasticJobConfig;
import com.rabbit.task.autoconfigure.JobZookeeperProperties;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Map;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2023/6/6 22:44
 */
public class ElasticJobConfigParser implements ApplicationListener<ApplicationReadyEvent> {

    private JobZookeeperProperties properties;

    private ZookeeperRegistryCenter zookeeperRegistryCenter;

    public ElasticJobConfigParser(JobZookeeperProperties properties, ZookeeperRegistryCenter zookeeperRegistryCenter) {
        this.properties = properties;
        this.zookeeperRegistryCenter = zookeeperRegistryCenter;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        ApplicationContext applicationContext = event.getApplicationContext();
        Map<String, Object> beans = applicationContext.getBeansWithAnnotation(ElasticJobConfig.class);
        beans.forEach((key, bean) -> {
            Class<?> aClass = bean.getClass();
            String jobClass = aClass.getName();
            if (jobClass.indexOf("$") > 0) {
                String className = jobClass;
                try {
                    aClass = Class.forName(className.substring(9, className.indexOf("$")));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                String jobTypeName = aClass.getInterfaces()[0].getSimpleName();
                ElasticJobConfig config = aClass.getAnnotation(ElasticJobConfig.class);
                // 获取所有job配置
                String corn = config.corn();
            }
        });
    }
}
