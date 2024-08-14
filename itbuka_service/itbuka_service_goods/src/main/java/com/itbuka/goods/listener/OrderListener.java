package com.itbuka.goods.listener;

import com.alibaba.fastjson.JSONObject;
import com.itbuka.goods.config.RabbitConfig;
import com.itbuka.goods.service.ProductDetailsService;
import com.itbuka.order.domain.Order;
import com.itbuka.order.domain.Task;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class OrderListener {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private ProductDetailsService service;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = RabbitConfig.ORDER_QUEUE)
    public void reducedInventory(String message){
        //格式转换
        Task task = JSONObject.parseObject(message, Task.class);
        //判断redis是否已经存在
        Object o = redisTemplate.opsForValue().get(task.getId());
        if(o != null){
            //存在，则无操作
            return;
        }
        //不存在，则存入redis，设置时间5分钟过期时间
        redisTemplate.opsForValue().set(task.getId(),task.getId(),5, TimeUnit.MINUTES);
        //格式转换
        Order order = JSONObject.parseObject(task.getRequestBody(), Order.class);
        //减库存
        Integer i = service.reduceStock(order.getProductId(), order.getNum());
        if(i != 1){
            //库存不足，发送消息给订单服务，通知订单服务，库存不足
            rabbitTemplate.convertAndSend(RabbitConfig.ORDER_EXCHANGE,RabbitConfig.ORDER_FAIL_QUEUE,order.getId());
        }
        //发消息告诉order服务删除task表中数据
        rabbitTemplate.convertAndSend(RabbitConfig.TASK_EXCHANGE,RabbitConfig.TASK_QUEUE,task.getId());


    }


    @RabbitListener(queues = RabbitConfig.ADD_QUEUE)
    public void addReducedInventory(String message){
        //格式转换
        Order order = JSONObject.parseObject(message, Order.class);
        //加库存
        Integer i = service.reduceStock(order.getProductId(), -order.getNum());
        if(i != 1){
            throw new RuntimeException("加库存失败");
        }
    }

}
