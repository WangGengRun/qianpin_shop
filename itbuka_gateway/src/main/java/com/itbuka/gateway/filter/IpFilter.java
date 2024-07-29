package com.itbuka.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
@Component
public class IpFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("经过第1个过滤器IpFilter");
        System.out.println(exchange.getRequest().getURI().getHost());
        return chain.filter(exchange);
    }
    @Override
    public int getOrder() {
        return 1;
    }
}
