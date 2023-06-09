package com.rabbit.task.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2023/6/5 23:21
 */
@Data
@ConfigurationProperties(prefix = "elastic.job.zk")
public class JobZookeeperProperties {

    private String namespace;

    private String serverLists;

    private int maxRetries = 3;

    private int connectionTimeoutMilliseconds = 15000;

    private int sessionTimeoutMilliseconds = 60000;

    private int baseSleepTimeMilliseconds = 1000;

    private int maxSleepTimeMilliseconds = 3000;

    private String digest = "";

}
