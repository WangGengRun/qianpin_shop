package com.itbuka.utils;

import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Map;

@Component
public class TokenDecode {

    /**
     * 获取用户信息
     * @return
     */
    public Map<String,String> getUserInfo(){
        //获取授权信息
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
        //令牌解码
        return parseJwt(details.getTokenValue());
    }

    /**
     * 读取令牌数据
     */
    public Map<String,String> parseJwt(String token){
        Claims claims = Jwts.parser().setSigningKey("itbuka".getBytes(StandardCharsets.UTF_8)).parseClaimsJws(token).getBody();
        return JSON.parseObject(JSON.toJSONString(claims),Map.class);
    }
}


