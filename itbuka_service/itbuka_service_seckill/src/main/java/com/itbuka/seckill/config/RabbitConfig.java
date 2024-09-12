package com.itbuka.seckill.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {
    //队列
    public static final String SECKILL_QUEUE = "seckill.queue";

    //交换机
    public static final String SECKILL_EXCHANGE = "seckill.exchange";


    //声明队列
    /*
     *   new Queue(QUEUE_EMAIL,true,false,false)
     *   durable="true" 持久化 rabbitmq重启的时候不需要创建新的队列
     *   auto-delete 表示消息队列没有在使用时将被自动删除 默认是false
     *   exclusive  表示该消息队列是否只在当前connection生效,默认是false
     */
    @Bean(SECKILL_QUEUE)
    public Queue RABBIT_QUEUE() {
        return new Queue(SECKILL_QUEUE,true);
    }
     //声明交换机
    @Bean(SECKILL_EXCHANGE)
    public Exchange RABBIT_EXCHANGE() {
        //durable(true) 持久化，mq重启之后交换机还在
        return ExchangeBuilder.fanoutExchange(SECKILL_EXCHANGE).durable(true).build();
    }

    //队列绑定交换机，指定routingKey
    @Bean
    public Binding RABBIT_EXCHANGE_QUEUE(@Qualifier(SECKILL_QUEUE) Queue queue, @Qualifier(SECKILL_EXCHANGE) Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(SECKILL_QUEUE).noargs();
    }
}
