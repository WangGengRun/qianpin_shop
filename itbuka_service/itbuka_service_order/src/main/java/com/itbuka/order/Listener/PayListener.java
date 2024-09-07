package com.itbuka.order.Listener;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.itbuka.alipay.domain.AliPay;
import com.itbuka.alipay.feign.PayFeign;
import com.itbuka.order.config.RabbitConfig;
import com.itbuka.order.domain.Order;
import com.itbuka.order.service.OrderService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PayListener {
    @Autowired
    private PayFeign payFeign;
    @Autowired
    private OrderService orderService;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @RabbitListener(queues = RabbitConfig.PAY_DEAD_QUEUE)
    public void pay(String orderStr){

        Order param = JSONObject.parseObject(orderStr, Order.class);
        Order orderSelect=new Order();
        orderSelect.setId(param.getId());
        Order order = orderService.selectList(orderSelect).get(0);
        if(order.getStatus()!=0){
            return ;
        }
        AliPay aliPay=new AliPay();
        aliPay.setTime(order.getCreateTime().getTime());
        Map query = payFeign.queryPayment(aliPay);

        if (query.get("msg").equals("Success")) {
            //成功订单补偿
            order.setTradeNo(query.get("trade_no").toString());
            order.setStatus(2);
        }else {

            order.setStatus(7);
      }
        orderService.update(order);
        rabbitTemplate.convertAndSend("",RabbitConfig.ADD_QUEUE, JSON.toJSONString(order));



    }
}
