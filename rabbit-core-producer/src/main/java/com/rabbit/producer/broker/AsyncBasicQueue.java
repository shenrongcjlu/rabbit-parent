package com.rabbit.producer.broker;

import com.sun.org.apache.bcel.internal.generic.NEW;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2023/5/28 15:55
 */
@Slf4j
public class AsyncBasicQueue {

    private static final int THREAD_SIZE = Runtime.getRuntime().availableProcessors();

    private static final int QUEUE_SIZE = 10000;

    private static ExecutorService senderAsync =
            new ThreadPoolExecutor(
                    THREAD_SIZE,
                    THREAD_SIZE,
                    60L,
                    TimeUnit.SECONDS,
                    new ArrayBlockingQueue<>(QUEUE_SIZE),
                    r -> {
                        Thread thread = new Thread();
                        thread.setName("rabbit_mq_client_async_sender");
                        return thread;
                    },
                    (r, executor) -> log.error("async sender is rejected, runnable: {}, execuror: {}", r, executor)
            );

    public static void submit(Runnable runnable) {
        senderAsync.submit(runnable);
    }
}
