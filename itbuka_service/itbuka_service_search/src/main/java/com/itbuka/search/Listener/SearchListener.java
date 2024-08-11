package com.itbuka.search.Listener;


import com.alibaba.fastjson.JSONObject;
import com.itbuka.damian.DetailIndex;
import com.itbuka.goods.domain.Detail;
import com.itbuka.goods.feign.GoodsFeign;
import com.itbuka.search.config.RabbitConfig;
import com.itbuka.search.service.ESManagerService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SearchListener {
    @Autowired
    private ESManagerService esManagerService;
    //上架索引库
    @RabbitListener(queues = RabbitConfig.GOODS_QUEUE)
    public void goods(String id){
    esManagerService.createMappingAndIndex();
    esManagerService.importById(id);
    }
    //下架索引库
    @RabbitListener(queues = RabbitConfig.DOWN_GOODS_QUEUE)
    public void downGoods(String id){
        esManagerService.deleteById(id);
    }
}
