package com.itbuka.goods.config;

import com.google.common.collect.Maps;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitConfig {
    //队列
    public static final String ORDER_QUEUE = "order.queue";

    public static final String TASK_QUEUE = "task.queue";

    public static final String ORDER_FAIL_QUEUE = "order.fail.queue";

    public static final String PAY_QUEUE = "pay.queue";

    public static final String PAY_DEAD_QUEUE = "pay.dead.queue";

    public static final String ADD_QUEUE = "add.queue";

    //交换机
    public static final String ORDER_EXCHANGE = "order.exchange";

    public static final String TASK_EXCHANGE = "task.exchange";


    public static final String PAY_EXCHANGE = "pay.exchange";

    //声明队列
    /*
     *   new Queue(QUEUE_EMAIL,true,false,false)
     *   durable="true" 持久化 rabbitmq重启的时候不需要创建新的队列
     *   auto-delete 表示消息队列没有在使用时将被自动删除 默认是false
     *   exclusive  表示该消息队列是否只在当前connection生效,默认是false
     */
    @Bean(ORDER_QUEUE)
    public Queue ORDER_QUEUE() {
        return new Queue(ORDER_QUEUE,true);
    }


    @Bean(TASK_QUEUE)
    public Queue TASK_QUEUE() {
        return new Queue(TASK_QUEUE,true);
    }

    @Bean(ORDER_FAIL_QUEUE)
    public Queue ORDER_FAIL_QUEUE() {
        return new Queue(ORDER_FAIL_QUEUE,true);
    }

    @Bean(PAY_QUEUE)
    public Queue PAY_QUEUE() {
        Map map = new HashMap();
        map.put("x-dead-letter-exchange",PAY_EXCHANGE);
        map.put("x-message-ttl",10000);
        return new Queue(PAY_QUEUE,true,false,false,map);
    }
    @Bean(ADD_QUEUE)
    public Queue ADD_QUEUE() {
        return new Queue(ADD_QUEUE,true);
    }


    @Bean(PAY_DEAD_QUEUE)
    public Queue PAY_DEAD_QUEUE() {
        return new Queue(PAY_DEAD_QUEUE,true);
    }
    //声明交换机
    @Bean(ORDER_EXCHANGE)
    public Exchange ORDER_EXCHANGE() {
        //durable(true) 持久化，mq重启之后交换机还在
        return ExchangeBuilder.directExchange(ORDER_EXCHANGE).durable(true).build();
    }

    //声明交换机
    @Bean(TASK_EXCHANGE)
    public Exchange TASK_EXCHANGE() {
        //durable(true) 持久化，mq重启之后交换机还在
        return ExchangeBuilder.fanoutExchange(TASK_EXCHANGE).durable(true).build();
    }

    //声明交换机
    @Bean(PAY_EXCHANGE)
    public Exchange PAY_EXCHANGE() {
        //durable(true) 持久化，mq重启之后交换机还在
        return ExchangeBuilder.fanoutExchange(PAY_EXCHANGE).durable(true).build();
    }


    //队列绑定交换机，指定routingKey
    @Bean
    public Binding ORDER_EXCHANGE_QUEUE(@Qualifier(ORDER_QUEUE) Queue queue, @Qualifier(ORDER_EXCHANGE) Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ORDER_QUEUE).noargs();
    }

    //队列绑定交换机，指定routingKey
    @Bean
    public Binding TASK_EXCHANGE_QUEUE(@Qualifier(TASK_QUEUE) Queue queue, @Qualifier(TASK_EXCHANGE) Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(TASK_QUEUE).noargs();
    }

    //队列绑定交换机，指定routingKey
    @Bean
    public Binding ORDER_FAIL_EXCHANGE_QUEUE(@Qualifier(ORDER_FAIL_QUEUE) Queue queue, @Qualifier(ORDER_EXCHANGE) Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ORDER_FAIL_QUEUE).noargs();
    }

    //队列绑定交换机，指定routingKey
    @Bean
    public Binding PAY_DEAD_EXCHANGE_QUEUE(@Qualifier(PAY_DEAD_QUEUE) Queue queue, @Qualifier(PAY_EXCHANGE) Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(PAY_DEAD_QUEUE).noargs();
    }





    public static final String RECEIVING_QUEUE = "receiving.queue";
    public static final String RECEIVING_DEAD_QUEUE = "receiving.dead.queue";
    public static final String RECEIVING_DEAD_EXCHANGE="receiving.dead.exchange";


    @Bean(RECEIVING_QUEUE)
    public Queue RECEIVING_QUEUE() {
        Map<String, Object> args = Maps.newHashMap();
        args.put("x-dead-letter-exchange","receiving.dead.exchange");
        args.put("x-message-ttl",1000*60*60*24*14);
        return new Queue(RECEIVING_QUEUE,true,false,false,args);
    }
    @Bean(RECEIVING_DEAD_QUEUE)
    public Queue RECEIVING_DEAD_QUEUE() {
        return new Queue(RECEIVING_DEAD_QUEUE,true);
    }
    @Bean(RECEIVING_DEAD_EXCHANGE)
    public Exchange RECEIVING_DEAD_EXCHANGE(){
        return ExchangeBuilder.fanoutExchange(RECEIVING_DEAD_EXCHANGE).durable(true).build();
    }
    @Bean
    public Binding RECEIVING_DEAD_EXCHANGE_QUEUE(@Qualifier(RECEIVING_DEAD_QUEUE)Queue queue, @Qualifier(RECEIVING_DEAD_EXCHANGE) Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("").noargs();
    }



}
