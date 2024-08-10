package com.itbuka.ad.Listener;

import com.itbuka.ad.config.RabbitConfig;
import com.itbuka.goods.domain.Detail;
import com.itbuka.goods.feign.GoodsFeign;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AdListener {
    @Autowired
    private GoodsFeign goodsFeign;
    @RabbitListener(queues = RabbitConfig.GOODS_QUEUE)
    public void goods(String id){

        Detail data=goodsFeign.findDetail(Long.valueOf(id)).getData();
    }
}
