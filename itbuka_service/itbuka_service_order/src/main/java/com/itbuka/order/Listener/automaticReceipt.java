package com.itbuka.order.Listener;

import com.alibaba.fastjson.JSONObject;
import com.itbuka.order.config.RabbitConfig;
import com.itbuka.order.domain.Order;
import com.itbuka.order.service.OrderService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class automaticReceipt {
    @Autowired
    private OrderService orderService;
    //自动收货
    @RabbitListener(queues = RabbitConfig.RECEIVING_QUEUE)
    public void RECEIVING_QUEUE(String message){
        List list = JSONObject.parseObject(message, List.class);
        for (Object o : list) {
            Order order=JSONObject.parseObject(o.toString(),Order.class);
            Order select = new Order();
            select.setId(order.getId());
            //进行状态校验
            order = orderService.selectList(select).get(0);
            if (order.getStatus() == 6) {
                return;
            }
            if (order.getStatus() != 3) {
                throw new RuntimeException("订单状态异常");
            }
            order.setStatus(6);
            order.setUpdateTime(new Date());
            orderService.update(order);
        }
    }

}
