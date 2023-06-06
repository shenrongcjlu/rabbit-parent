package com.rabbit.task.autoconfigure;

import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import com.rabbit.task.parser.ElasticJobConfigParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2023/6/5 23:16
 */
@Slf4j
@Configuration
@ConditionalOnProperty(prefix = "elastic.job.zk", name = {"namespace", "serverLists"})
@EnableConfigurationProperties(JobZookeeperProperties.class)
public class JobParserAutoConfiguration {

    @Bean(initMethod = "init")
    public ZookeeperRegistryCenter zookeeperRegistryCenter(JobZookeeperProperties properties) {
        ZookeeperConfiguration zkConfig = new ZookeeperConfiguration(properties.getServerLists(), properties.getNamespace());
        zkConfig.setBaseSleepTimeMilliseconds(properties.getBaseSleepTimeMilliseconds());
        zkConfig.setMaxSleepTimeMilliseconds(properties.getMaxSleepTimeMilliseconds());
        zkConfig.setConnectionTimeoutMilliseconds(properties.getConnectionTimeoutMilliseconds());
        zkConfig.setSessionTimeoutMilliseconds(properties.getSessionTimeoutMilliseconds());
        zkConfig.setMaxRetries(properties.getMaxRetries());
        zkConfig.setDigest(properties.getDigest());
        log.info("init ZookeeperRegistryCenter success, zkAddress: {}, zkNamespace: {}", properties.getServerLists(), properties.getNamespace());
        return new ZookeeperRegistryCenter(zkConfig);
    }

    @Bean
    public ElasticJobConfigParser elasticJobConfigParser(JobZookeeperProperties properties, ZookeeperRegistryCenter zookeeperRegistryCenter) {
        return new ElasticJobConfigParser(properties, zookeeperRegistryCenter);
    }

}
