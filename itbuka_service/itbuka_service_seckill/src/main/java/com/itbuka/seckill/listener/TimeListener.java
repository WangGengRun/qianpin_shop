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

@Component
public class TimeListener {
    @Autowired
    private SeckillProductMapper seckillProductMapper;
    @Autowired
    private SeckillMapper seckillMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    //定时任务每天23点执行
    @Scheduled(cron = "0 0 23 * * ?")
    public void timeListener() {
        //获取第二天的日期
        Date date = new Date(new Date().getTime() + 1000 * 60 * 60);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd ");
        String format = simpleDateFormat.format(date);
        String start = format+"00:00:00";
        String end = format+"23:59:59";
        //把符合条件的秒杀活动查询出来
        LambdaQueryWrapper<Seckill> lqw = new LambdaQueryWrapper<>();
        lqw.ge(Seckill::getStartTime,start);
        lqw.le(Seckill::getEndTime,end);
        lqw.eq(Seckill::getIsDelete,0);
        //查询秒杀活动
        List<Seckill> seckills = seckillMapper.selectList(lqw);
        //根绝秒杀活动查询出对应的商品
        if (seckills != null && seckills.size() > 0) {
            for (Seckill seckill : seckills) {
                LambdaQueryWrapper<SeckillProduct> lqwP = new LambdaQueryWrapper<>();
                lqwP.eq(SeckillProduct::getSeckillId,seckill.getId());
                lqwP.eq(SeckillProduct::getIsDelete,0);
                List<SeckillProduct> select = seckillProductMapper.selectList(lqwP);
                for (SeckillProduct product : select) {
                    //最外层 是 年月日+时间场次 里面存的是 商品id+商品
                    redisTemplate.boundHashOps(format+product.getActivityTimes()).put(product.getId(),product);
                    //把秒杀的商品场次存到redis中
                    redisTemplate.opsForSet().add(format,product.getActivityTimes());
                }
            }
        }
    }
}
