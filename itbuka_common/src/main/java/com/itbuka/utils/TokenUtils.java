package com.itbuka.utils;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class TokenUtils {
    public static String getToken(){
        //获取token
        Map map = new HashMap();
        map.put("grant_type","client_credentials");
        map.put("client_id","itbuka");
        map.put("client_secret","123456");
        String post = HttpUtil.post("http://localhost:9200/oauth/token", map);
        Map token = JSONObject.parseObject(post, Map.class);
        String accessToken = token.get("access_token").toString();
        return accessToken;
    }
}