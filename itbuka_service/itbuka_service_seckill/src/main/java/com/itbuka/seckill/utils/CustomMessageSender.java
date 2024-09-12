package com.itbuka.seckill.utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class CustomMessageSender implements RabbitTemplate.ConfirmCallback {

    static final Logger log = LoggerFactory.getLogger(CustomMessageSender.class);

    private static final String MESSAGE_CONFIRM_="message_confirm_";

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private RedisTemplate redisTemplate;

    public CustomMessageSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        rabbitTemplate.setConfirmCallback(this);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack){
            //返回成功通知
            //删除redis中的相关数据
            redisTemplate.delete(correlationData.getId());
            redisTemplate.delete(MESSAGE_CONFIRM_+correlationData.getId());
        }else{
            //返回失败通知
            Map<String,Object> map =(Map<String,Object>)redisTemplate.opsForHash().entries(MESSAGE_CONFIRM_+correlationData.getId());
            String exchange = map.get("exchange").toString();
            String routingKey = map.get("routingKey").toString();
            Object sendMessage = map.get("sendMessage");

            //重新发送
            rabbitTemplate.convertAndSend(exchange,routingKey, sendMessage,correlationData);
        }
    }

    //自定义发送方法
    public void sendMessage(String exchange,String routingKey,Object message){

        //设置消息唯一标识并存入缓存
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        redisTemplate.opsForValue().set(correlationData.getId(),message);

        //本次发送到相关元信息存入缓存
        Map<String, Object> map = new HashMap<>();
        map.put("exchange", exchange);
        map.put("routingKey", routingKey);
        map.put("sendMessage", message);
        redisTemplate.opsForHash().putAll(MESSAGE_CONFIRM_+correlationData.getId(),map);

        //携带唯一标识发送消息
        rabbitTemplate.convertAndSend(exchange,routingKey,message,correlationData);
    }
}

