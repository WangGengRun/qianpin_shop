package com.itbuka.canal.listener;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.itbuka.canal.config.RabbitConfig;
import com.xpand.starter.canal.annotation.CanalEventListener;
import com.xpand.starter.canal.annotation.ListenPoint;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

@CanalEventListener //声明当前的类是canal的监听类
public class AdListener {
    /**
     *
     * @param eventType 当前操作数据库的类型
     * @param rowData 当前操作数据库的数据
     */

    @Autowired
    public RabbitTemplate rabbitTemplate;
    @ListenPoint(schema = "b0394",table = "ad")
    public void adUpdate(CanalEntry.EventType eventType, CanalEntry.RowData rowData){
        rabbitTemplate.convertAndSend(RabbitConfig.AD_EXCHANGE,RabbitConfig.AD_QUEUE,"1");

    }
}
