package com.itbuka.seckill.service.impl;
import com.itbuka.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RandomServiceImpl {
    @Autowired
    private RedisTemplate redisTemplate;

    public String getRandomString(String username) {
        String randomString = RandomUtil.getRandomString();
        redisTemplate.opsForValue().set("random_"+username,randomString);
        return randomString;
    }
}

