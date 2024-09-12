package com.itbuka.seckill.service;

import java.util.List;
import java.util.Map;

public interface SeckillIndexService {
    /**
     * 查询秒杀商品列表
     * @return
     */
    Map<String, List> index();

}
