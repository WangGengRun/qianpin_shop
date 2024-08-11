package com.itbuka.canal.listener;

import com.alibaba.fastjson.JSON;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.itbuka.canal.config.RabbitConfig;
import com.xpand.starter.canal.annotation.CanalEventListener;
import com.xpand.starter.canal.annotation.ListenPoint;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
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
    @ListenPoint(schema = "b0394",table = "goods_product")
    public void product(CanalEntry.EventType eventType, CanalEntry.RowData rowData){
        System.out.println("商品表数据发生改变");
        //获取改变之前的数据
        List<CanalEntry.Column> beforeColumnsList = rowData.getBeforeColumnsList();
        //获取改变之后的数据
        List<CanalEntry.Column> afterColumnsList = rowData.getAfterColumnsList();
        Map<String,String> map = new HashMap();
        for (CanalEntry.Column column : beforeColumnsList) {
            if (column.getName().equals("status")){
                map.put("before",column.getValue());
            }
        }
        for (CanalEntry.Column column : afterColumnsList) {
            if (column.getName().equals("status")){
                map.put("after",column.getValue());
            }
            if (column.getName().equals("id")){
                map.put("id",column.getValue());
            }
        }
        if (Integer.valueOf(map.get("before")) == 0 && Integer.valueOf(map.get("after")) == 1){
            rabbitTemplate.convertAndSend(RabbitConfig.GOODS_EXCHANGE,RabbitConfig.GOODS_QUEUE,map.get("id"));
        }

        if (Integer.valueOf(map.get("before")) == 1 && Integer.valueOf(map.get("after")) == 0){
            rabbitTemplate.convertAndSend(RabbitConfig.GOODS_EXCHANGE,RabbitConfig.DOWN_GOODS_QUEUE,map.get("id"));
        }


    }
}


