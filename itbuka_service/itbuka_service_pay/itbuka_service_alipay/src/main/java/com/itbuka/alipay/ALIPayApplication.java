package com.itbuka.alipay;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;

import com.itbuka.alipay.config.AliPayConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient
//@EnableFeignClients(basePackages = "com.itbuka.alipay.feign")
public class ALIPayApplication {
    @Autowired
    private AliPayConfig aliPayConfig;

    public static void main(String[] args) {
        SpringApplication.run(ALIPayApplication.class, args);
    }

    @Bean
    public AlipayClient alipayClient() {
        return new DefaultAlipayClient(aliPayConfig.getGatewayHost(), aliPayConfig.getAppId(), aliPayConfig.getAppPrivateKey(), "JSON", "utf-8", aliPayConfig.getAlipayPublicKey(), "RSA2");
    }
}
