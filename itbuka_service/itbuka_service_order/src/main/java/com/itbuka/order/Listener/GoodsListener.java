package com.itbuka.order.Listener;

import com.itbuka.order.config.RabbitConfig;
import com.itbuka.order.mapper.OrderMapper;
import com.itbuka.order.mapper.TaskMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GoodsListener {
    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private OrderMapper orderMapper;

    @RabbitListener(queues = RabbitConfig.TASK_QUEUE)
    public void task(Long taskId){
        taskMapper.deleteById(taskId);
    }

    @RabbitListener(queues = RabbitConfig.ORDER_FAIL_QUEUE)
    public void orderFail(Long orderId){
        orderMapper.deleteById(orderId);
    }

}
