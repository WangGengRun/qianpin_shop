package com.itbuka.cart;

import com.itbuka.utils.TokenDecode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableDiscoveryClient
public class CartApplication {
    public static void main(String[] args) {
        SpringApplication.run(CartApplication.class,args);
        System.out.println("购物车启动成功");
    }
    @Bean
    public TokenDecode tokenDecode(){
        return new TokenDecode();
    }
}

