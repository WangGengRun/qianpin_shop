package com.itbuka.order.Listener;

import com.itbuka.order.domain.Task;
import com.itbuka.order.service.TaskService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Scheduling {
    @Autowired
    private TaskService taskService;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Scheduled(cron = "0 0/2 * * * ?")
    public void scheduling(){
        List<Task> tasks = taskService.selectAll();
        if (tasks.size() == 0) {
            return;
        }
        for (Task task : tasks) {
            rabbitTemplate.convertAndSend(task.getMqExchange(),task.getMqRoutingkey(),task.getRequestBody());
        }

    }

}
