package com.itbuka.seckill.service;

import com.itbuka.order.domain.Order;
import com.itbuka.seckill.domain.SeckillProduct;

public interface SecKillOrderService {
    /**
     * 秒杀下单
     * @return
     */
    boolean add(Order order,String random);

}
