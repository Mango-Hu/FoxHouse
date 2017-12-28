package com.fox.demo.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;


public class Test1 {

    @Resource
    CacheManager cacheManager;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test1() {
        String name = new Father().getName();

        if ("1".equals(name)) {
            System.out.println(1111);
        } else {
            System.out.println(name);
        }
    }

    @Test
    public void testCache(){

        Cache cache =cacheManager.getCache("1");
        cache.put("test",666);
        System.out.println(cache);
    }

    @Test
    public void testCache1(){

        redisTemplate.opsForValue().set("test","666");
        System.out.println(redisTemplate.opsForValue().get("test"));
    }



    @Test
    public void testRedis(){

        Jedis jedis =new Jedis("127.0.0.1");
        System.out.println("OK");
        System.out.println(jedis.ping());
        jedis.set("test","fox");
        System.out.println(jedis.get("test"));
        jedis.lpush("list","1");
        jedis.lpush("list","2");
        jedis.lpush("list","3");
        jedis.lpush("list","4");
        System.out.println(jedis.lrange("list",0,3));
        System.out.println(jedis.lindex("list",0));
    }


    @Test
    public void testFile(){
        String uniqueWords = null;
        try(Stream<String> lines = Files.lines(Paths.get("C://test.txt"), Charset.defaultCharset())){
            Stream<String> stream = lines.flatMap(line -> Arrays.stream(line.split(","))).distinct();
            System.out.println(stream.toString());
        }
        catch(IOException e){

        }
    }



}

