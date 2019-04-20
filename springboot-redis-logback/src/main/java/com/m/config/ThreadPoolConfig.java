package com.m.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableAsync
public class ThreadPoolConfig {

    @Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //设置核心线程数
        executor.setCorePoolSize(2);
        //设置最大线程数
        executor.setMaxPoolSize(5);
        //设置队列容量
        executor.setQueueCapacity(2);
        //设置线程活跃时间
        executor.setKeepAliveSeconds(60);
        //设置线程名称前缀
        executor.setThreadNamePrefix("thread-");
        // 设置拒绝策略，当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 等待所有线程执行再关闭线程池
        executor.setWaitForTasksToCompleteOnShutdown(true);

        return executor;
    }
}
