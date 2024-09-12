package com.itbuka.order.Listener;

import com.alibaba.fastjson.JSONObject;
import com.itbuka.order.config.RabbitConfig;
import com.itbuka.order.domain.Order;
import com.itbuka.order.service.OrderService;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class SecKillListener {
    @Autowired
    private OrderService orderService;
    @RabbitListener(queues = RabbitConfig.SECKILL_QUEUE)
    public void secKill(Channel channel, Message message){
            try {
                //流量消峰
                 channel.basicQos(300);
                 Order order = JSONObject.parseObject(message.getBody(), Order.class);
                 Integer insert = orderService.insert(order);
                 //返回失败通知
                 if (insert != 1) {
                     //第一个boolean true所有消费者都会拒绝这个消息，false代表只有当前消费者拒绝
                     //第二个boolean true当前消息会进入到死信队列，false重新回到原有队列中，默认回到头部

                     channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
                 }
                 //成功拒绝消息
                     channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);

            } catch (IOException e) {
               throw new RuntimeException(e);
            }



    }
}
