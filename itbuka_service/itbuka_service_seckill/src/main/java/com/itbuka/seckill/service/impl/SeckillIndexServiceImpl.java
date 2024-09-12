package com.itbuka.seckill.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.itbuka.seckill.service.SeckillIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class SeckillIndexServiceImpl implements SeckillIndexService {
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public Map<String, List> index() {
        Map map = new HashMap();
        //获取今天所有的秒杀商品
        Date date = new Date(new Date().getTime());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd ");
        String format = simpleDateFormat.format(date);
        SimpleDateFormat simple = new SimpleDateFormat("HH");
        Integer hour = Integer.valueOf(simple.format(date));
        if (hour%2 != 0) {
            hour = hour-1;
        }
        //获取所有时间场次
        Object o = redisTemplate.boundValueOps("Seckill_"+format).get();
        Set<String> pop = JSONObject.parseObject(JSONObject.toJSONString(o), Set.class);
        for (String s : pop) {
            Integer i = Integer.valueOf(s.split(":")[0]);
            if (hour <= i) {
                //根据时间场次获取对应的商品列表
                List values = redisTemplate.boundHashOps(format + s).values();
                map.put(s, values);
            }
        }
        return map;
    }
}
