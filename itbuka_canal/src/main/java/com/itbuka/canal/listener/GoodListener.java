package com.itbuka.canal.listener;

import com.alibaba.fastjson.JSON;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.itbuka.canal.config.RabbitConfig;
import com.xpand.starter.canal.annotation.CanalEventListener;
import com.xpand.starter.canal.annotation.ListenPoint;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

@CanalEventListener //声明当前的类是canal的监听类
public class GoodListener {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    /**
     *
     * @param eventType 当前操作数据库的类型
     * @param rowData 当前操作数据库的数据
     */
    @ListenPoint(schema = "itbuka_goods",table = "goods_product")
    public void product(CanalEntry.EventType eventType, CanalEntry.RowData rowData){
        Map map = new HashMap<>();
        for (CanalEntry.Column column : rowData.getBeforeColumnsList()) {
            if (column.getName().equals("status")) {
                map.put("old",column.getValue());
            }
        }
        for (CanalEntry.Column column : rowData.getAfterColumnsList()) {
            if (column.getName().equals("status")) {
                map.put("new",column.getValue());
            }
            if (column.getName().equals("id")) {
                map.put(column.getName(),column.getValue());
            }
        }
        // 判断是否是上架
        if (map.get("old").equals("0") && map.get("new").equals("1")) {
            rabbitTemplate.convertAndSend(RabbitConfig.GOODS_EXCHANGE, RabbitConfig.GOODS_QUEUE, JSON.toJSONString(map.get("id")));
        }

    }
}


