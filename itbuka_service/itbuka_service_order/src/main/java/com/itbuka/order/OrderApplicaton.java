package com.itbuka.order;

import com.itbuka.Interceptor.FeignInterceptor;
import com.itbuka.utils.TokenDecode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {
        "com.itbuka.goods.feign","com.itbuka.cart.feign","com.itbuka.alipay.feign"
})
@EnableScheduling
public class OrderApplicaton {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplicaton.class, args);
    }
    @Bean
    public FeignInterceptor feignInterceptor(){
        return new FeignInterceptor();
}
    @Bean
    public TokenDecode tokenDecode(){
        return new TokenDecode();
    }
}
