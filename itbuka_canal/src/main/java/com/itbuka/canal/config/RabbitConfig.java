package com.itbuka.canal.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {
    //队列
    public static final String AD_QUEUE = "ad.queue";
    public static final String AD_QUEUE1 = "ad.queue1";
    public static final String GOODS_QUEUE = "goods.queue";
    //交换机
    public static final String AD_EXCHANGE = "ad.exchange";
    public static final String GOODS_EXCHANGE = "goods.exchange";

    //声明队列
    /*
     *   new Queue(QUEUE_EMAIL,true,false,false)
     *   durable="true" 持久化 rabbitmq重启的时候不需要创建新的队列
     *   auto-delete 表示消息队列没有在使用时将被自动删除 默认是false
     *   exclusive  表示该消息队列是否只在当前connection生效,默认是false
     */
    @Bean(AD_QUEUE)
    public Queue RABBIT_QUEUE() {
        return new Queue(AD_QUEUE,true);
    }
    @Bean(AD_QUEUE1)
    public Queue RABBIT_QUEUE1() {
        return new Queue(AD_QUEUE1,true);
    }
    @Bean(GOODS_QUEUE)
    public Queue GOODS_QUEUE() {
        return new Queue(GOODS_QUEUE,true);
    }
    //声明交换机
    @Bean(AD_EXCHANGE)
    public Exchange RABBIT_EXCHANGE() {
        //durable(true) 持久化，mq重启之后交换机还在
        return ExchangeBuilder.fanoutExchange(AD_EXCHANGE).durable(true).build();
    }
    @Bean(GOODS_EXCHANGE)
    public Exchange GOODS_EXCHANGE() {
        //durable(true) 持久化，mq重启之后交换机还在
        return ExchangeBuilder.fanoutExchange(GOODS_EXCHANGE).durable(true).build();
    }

    //队列绑定交换机，指定routingKey
    @Bean
    public Binding RABBIT_EXCHANGE_QUEUE(@Qualifier(AD_QUEUE) Queue queue, @Qualifier(AD_EXCHANGE) Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(AD_QUEUE).noargs();
    }
    @Bean
    public Binding RABBIT_EXCHANGE_QUEUE1(@Qualifier(AD_QUEUE1) Queue queue, @Qualifier(AD_EXCHANGE) Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(AD_QUEUE1).noargs();
    }
    @Bean
    public Binding GOODS_EXCHANGE_QUEUE(@Qualifier(GOODS_QUEUE) Queue queue, @Qualifier(GOODS_EXCHANGE) Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(GOODS_QUEUE).noargs();
    }
}
