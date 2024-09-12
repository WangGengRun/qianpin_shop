package com.itbuka.seckill.listener;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.itbuka.seckill.domain.Seckill;
import com.itbuka.seckill.domain.SeckillProduct;
import com.itbuka.seckill.mapper.SeckillMapper;
import com.itbuka.seckill.mapper.SeckillProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Component
public class TimeListener {
    @Autowired
    private SeckillProductMapper seckillProductMapper;
    @Autowired
    private SeckillMapper seckillMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    @Scheduled(cron = "0 0 23 * * ?")
    public void timeListener() {
        //获取时间
        Date date = new Date(new Date().getTime() + 1000 * 60 * 60);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd ");
        String startTime = simpleDateFormat.format(date) + "00:00:00";
        String endTime = simpleDateFormat.format(date) + "23:59:59";
        //查询出符合条件的商品
        LambdaQueryWrapper<Seckill> lqw = new LambdaQueryWrapper();
        lqw.ge(Seckill::getStartTime, startTime);
        lqw.le(Seckill::getStartTime, endTime);
        List<Seckill> seckills = seckillMapper.selectList(lqw);
        //获取每一个时间段名称,用于后续redis中key的设置
        Set<String> set = new TreeSet();
        for (Seckill seckill : seckills) {
            LambdaQueryWrapper<SeckillProduct> lambdaQueryWrapper = new LambdaQueryWrapper();
            //状态必须为审核通过 status=1  商品库存个数>0
            lambdaQueryWrapper.eq(SeckillProduct::getIsDelete,0);
            lambdaQueryWrapper.ge(SeckillProduct::getNum,0);
            lambdaQueryWrapper.eq(SeckillProduct::getSeckillId,seckill.getId());
            List<SeckillProduct> seckillProducts = seckillProductMapper.selectList(lambdaQueryWrapper);
            for (SeckillProduct seckillProduct : seckillProducts) {
                set.add(seckillProduct.getActivityTimes());
                //存放秒杀的商品
                redisTemplate.boundHashOps(simpleDateFormat.format(date)+seckillProduct.getActivityTimes()).put(seckillProduct.getId(),seckillProduct);
                //存放秒杀的库存
                redisTemplate.opsForValue().set("Inventory_"+seckillProduct.getId(),seckillProduct.getNum());
            }
        }
        //存放秒杀的时间场次
        redisTemplate.boundValueOps("Seckill_"+simpleDateFormat.format(date)).set(set);
    }
}
