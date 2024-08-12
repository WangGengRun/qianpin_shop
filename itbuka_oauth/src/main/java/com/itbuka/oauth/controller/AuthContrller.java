package com.itbuka.oauth.controller;

import com.itbuka.entity.Result;
import com.itbuka.oauth.service.AuthService;
import com.itbuka.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 认证中心
 */
@RestController
@RequestMapping("/auth")
public class AuthContrller {
    @Autowired
    private AuthService authService;
    @Value("${auth.clientId}")
    private String clientId;
    @Value("${auth.clientSecret}")
    private String clientSecret;
    @Value("${auth.ttl}")
    private Integer ttl;
    /**
     * 登录
     */
    @RequestMapping("/login")
    public Result login(@RequestParam String username, @RequestParam String password, HttpServletResponse response){
        Map<String,String> login = authService.login(username, password, clientId, clientSecret,ttl);
        CookieUtil.addCookie(response,"localhost","/","token",login.get("jti"),-1,false);

        return Result.ok("登陆成功",login);
    }
}
