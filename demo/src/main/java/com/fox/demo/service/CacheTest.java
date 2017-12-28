package com.fox.demo.service;

import com.fox.demo.model.Girl;
import com.fox.demo.repository.GirlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.annotation.Resource;
@Transactional
@Service
public class CacheTest {

    @Resource
    CacheManager cacheManager;

    @Autowired
    GirlRepository girlRepository;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    public void test1() {
        redisTemplate.opsForValue().set("test","666");
        if (redisTemplate.hasKey("test")){
            System.out.println("6666666666666666666666666666666");
        }
        System.out.println(redisTemplate.opsForValue().get("test").toString());
    }

    public void test2() {
         Cache cache = cacheManager.getCache("test");
        String s =cache.get("111",String.class);
        System.out.println(s);
        System.out.println(cache);
        cache.put("test","fox");
        System.out.println(cache.get("test",String.class));
    }

    public void test3() {
        stringRedisTemplate.opsForValue().set("test","777");
        if (stringRedisTemplate.hasKey("test")){
            System.out.println("555555555555555555555555");
        }
        System.out.println(stringRedisTemplate.opsForValue().get("test").toString());
    }

    public void test4(){
         stringRedisTemplate.opsForValue().set("sss","aaa");
        System.out.println(stringRedisTemplate.getKeySerializer().toString());
        System.out.println(stringRedisTemplate.getValueSerializer().toString());
    }

    @Cacheable(value = "girls")
    public Girl testCache() {
        System.out.println("看有没有运行");
        return girlRepository.findGirlById(3);
    }

/*    public Girl testCache() {
        System.out.println("看有没有运行");
        return girlRepository.findGirlById(3);
    }*/
}
