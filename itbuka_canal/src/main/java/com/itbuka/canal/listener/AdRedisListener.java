package com.itbuka.canal.listener;

import com.itbuka.canal.config.RabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AdRedisListener {
    @Autowired
    public RestTemplate restTemplate;
    @RabbitListener(queues = RabbitConfig.AD_QUEUE)
    public void message(String message){
        String forObject = restTemplate.getForObject("http://192.168.88.139/ad_load", String.class);
        System.out.println(forObject);
//        //能够获取到rabbitmq队列中的信息
//        System.out.println(message);
    }

}

