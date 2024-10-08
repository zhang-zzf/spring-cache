package com.feng.learn.github.demo.cache;

import com.feng.learn.github.demo.cache.config.degrade.CacheDegrade;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.function.Function;

import static com.feng.learn.github.demo.cache.config.degrade.SpringCacheConfig.CACHE_RESOLVER;
import static com.feng.learn.github.demo.cache.config.degrade.SpringCacheConfig.JVM_CACHE;

/**
 * @author zhanfeng.zhang
 * @date 2020/04/16
 */
@Service
@CacheConfig(cacheResolver = CACHE_RESOLVER)
@CacheDegrade("cache.person.degrade")
public class PersonServiceWithDegradeCacheResolver {

    final Function justForTest;

    public PersonServiceWithDegradeCacheResolver(Function justForTest) {
        this.justForTest = justForTest;
    }

    @Cacheable(key = "'/p/' + #root.args[0]", cacheNames = {JVM_CACHE})
    public Person getById(Long id) {
        justForTest.apply(null);
        return new Person().setId(id).setName("zhanfeng.zhang")
                .setHome(new Person.Address().setCity("Shanghai"))
                .setWork(new Person.Address().setStreet("Zhenbei road"));
    }

    @CacheEvict(key = "'/p/' + #root.args[0]", cacheNames = {JVM_CACHE})
    public void deleteById(Long id) {

    }

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    @Accessors(chain = true)
    public static class Person {

        Long id;
        String name;

        Address home;
        Address work;

        @Data
        @Accessors(chain = true)
        @FieldDefaults(level = AccessLevel.PRIVATE)
        public static class Address {

            String province;
            String city;
            String street;
        }
    }

}
