package com.itbuka.seckill.controller;

import com.itbuka.entity.Result;
import com.itbuka.seckill.service.SeckillIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 秒杀首页

 */
@RestController
@RequestMapping("seckillIndex")
public class SeckillIndexController {
    @Autowired
    private SeckillIndexService seckillIndexService;

    /**
     * 获取秒杀首页数据
     * @return
     */
    @RequestMapping("index")
    public Result index() {
        Map<String, List> index = seckillIndexService.index();
        return new Result("获取秒杀信息成功", index);
    }
}

