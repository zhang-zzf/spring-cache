package com.feng.learn.github.springcache.infrastructure.spring.cache;

import java.util.concurrent.TimeUnit;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhanfeng.zhang
 * @date 2020/03/25
 */
@Configuration
@EnableCaching
public class SpringCacheConfig {

    public static final String JVM_CACHE_Manage = "jvmCacheManage";

    @Bean(JVM_CACHE_Manage)
    public CacheManager jvmCacheManager() {
        return new ConcurrentMapCacheManager();
    }

}
