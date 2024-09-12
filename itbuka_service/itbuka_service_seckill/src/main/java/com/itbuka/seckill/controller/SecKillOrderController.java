package com.itbuka.seckill.controller;

import com.itbuka.entity.Result;
import com.itbuka.order.domain.Order;
import com.itbuka.seckill.service.SecKillOrderService;
import com.itbuka.seckill.utils.AccessLimit;
import com.itbuka.utils.TokenDecode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 秒杀订单
 */
@RestController
@RequestMapping("/seckillOrder")
public class SecKillOrderController {
    @Autowired
    private SecKillOrderService secKillOrderService;

    /**
     * 秒杀下单
     * @param order
     * @return
     */
    @PostMapping("/add")
    @AccessLimit
    public Result add(@RequestBody Order order, @RequestParam String random){
        boolean add = secKillOrderService.add(order,random);
        if (add){
            return Result.ok("秒杀下单成功");
        }else {
            return Result.fail("秒杀下单失败");
        }
    }
}

