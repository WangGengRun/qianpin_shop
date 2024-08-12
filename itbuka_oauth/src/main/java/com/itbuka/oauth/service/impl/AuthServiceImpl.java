package com.itbuka.oauth.service.impl;


import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.itbuka.oauth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class AuthServiceImpl implements AuthService {
@Autowired
private RestTemplate restTemplate;
@Autowired
private RedisTemplate redisTemplate;

    @Override
    public Map login(String username, String password, String clientId, String clientSecret,Integer ttl) {
        Map param=new HashMap();
        param.put("username",username);
        param.put("password",password);
        param.put("client_id",clientId);
        param.put("client_secret",clientSecret);
        param.put("grant_type","password");
        String post= HttpUtil.post("http://localhost:9200/oauth/token",param);
        Map<String,String> map= JSONObject.parseObject(post,Map.class);

        redisTemplate.opsForValue().set(map.get("jti"),map.get("access_token"),ttl, TimeUnit.HOURS);

        return map;
    }
}

