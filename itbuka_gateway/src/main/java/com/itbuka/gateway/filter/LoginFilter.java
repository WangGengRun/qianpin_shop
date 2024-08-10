package com.itbuka.gateway.filter;

import com.itbuka.gateway.utils.JwtUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
public class LoginFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        String path = request.getURI().getPath();
        //判断是不是登录接口
        if (path.contains("/login")) {
            return chain.filter(exchange);
         }
        //判断有没有token
        String token = request.getHeaders().getFirst("token");
        if (token == null) {
            //如果没有，拒绝访问
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }
        try {
            //解析成功放行
            Map map = JwtUtils.get(token);
        } catch (Exception e) {
            //解析失败，拒绝访问
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();

        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
