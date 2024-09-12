package com.itbuka.seckill.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.itbuka.order.domain.Order;
import com.itbuka.order.feign.OrderFeign;
import com.itbuka.seckill.config.RabbitConfig;
import com.itbuka.seckill.domain.SeckillProduct;
import com.itbuka.seckill.service.SecKillOrderService;
import com.itbuka.seckill.utils.CustomMessageSender;
import com.itbuka.utils.TokenDecode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class SecKillOrderServiceImpl implements SecKillOrderService {
    @Autowired
    private TokenDecode tokenDecode;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private CustomMessageSender customMessageSender;
    @Autowired
    private OrderFeign orderFeign;
    @Override
    public boolean add(Order order,String random) {
        String userName = tokenDecode.getUserInfo().get("user_name");
        order.setBuyerName(userName);
        //秒杀下单接口隐藏
        String randomStr = redisTemplate.opsForValue().get("random_" + order.getBuyerName()).toString();
        if (randomStr == null || !randomStr.equals(random)) {
            return false;
        }
        //防止恶意刷单，5分钟内只能购买一次
        String redisKey = "seckill_user_" + order.getBuyerName() + "_id_" + order.getProductId();
        long count = redisTemplate.opsForValue().increment(redisKey, 1);
        if (count != 1) {
            return false;
        }
        //设置有效期五分钟
        redisTemplate.expire(redisKey, 5, TimeUnit.MINUTES);

        //判断是否已经购买过
        Order select = new Order();
        select.setBuyerName(userName);
        select.setProductId(order.getProductId());
        List<Order> data = orderFeign.select(select).getData();
        if (data != null && data.size() > 0) {
            throw new RuntimeException("请勿重复购买");
        }
        //查询库存是否充足
        Object o = redisTemplate.opsForValue().get("Inventory_"+order.getProductId());
        if(o == null || Integer.valueOf(o.toString())<order.getNum()){
            return false;
        }
        //减库存
        Long decrement = redisTemplate.opsForValue().decrement("Inventory_" + order.getProductId(), order.getNum());
        if(decrement<0){
            return false;
        }
        if(decrement==0){
            redisTemplate.delete("Inventory_" + order.getProductId());
        }

        //获取redis key
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd ");
        String format = simpleDateFormat.format(date);
        SimpleDateFormat simple = new SimpleDateFormat("HH");
        Integer hour = Integer.valueOf(simple.format(date));
        if (hour%2 != 0) {
            hour = hour-1;
        }
        //获取秒杀商品
        SeckillProduct seckillProduct = JSONObject.parseObject(JSON.toJSONString(redisTemplate.boundHashOps(format + hour + ":00").get(order.getProductId())), SeckillProduct.class);
        order.setType(2);
        order.setProductId(seckillProduct.getProductId());
        order.setMoney(BigDecimal.valueOf(order.getNum()*seckillProduct.getSeckillMoney()));
        order.setStatus(0);
        customMessageSender.sendMessage(RabbitConfig.SECKILL_EXCHANGE,"",JSON.toJSONString(order
        ));
         return true;
    }
}
