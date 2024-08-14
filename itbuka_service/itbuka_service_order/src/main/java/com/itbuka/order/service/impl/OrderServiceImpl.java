package com.itbuka.order.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;


import com.github.pagehelper.PageHelper;
import com.itbuka.cart.feign.CartFeign;
import com.itbuka.goods.domain.ProductDetails;
import com.itbuka.goods.feign.GoodsFeign;
import com.itbuka.order.config.RabbitConfig;
import com.itbuka.order.domain.Order;
import com.itbuka.order.domain.Task;
import com.itbuka.order.mapper.OrderMapper;
import com.itbuka.order.mapper.TaskMapper;
import com.itbuka.order.service.OrderService;
import com.itbuka.utils.TokenDecode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author 王庚润
* @description 针对表【itbuka_order】的数据库操作Service实现
* @createDate 2024-08-13 10:08:18
*/
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper iOrderMapper;
    @Autowired
    private GoodsFeign goodsFeign;
    @Autowired
    private TokenDecode tokenDecode;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private CartFeign cartFeign;


    @Override
    public List<Order> selectAll() {
        LambdaQueryWrapper<Order> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Order::getIsDelete,0);
        return iOrderMapper.selectList(lqw);

    }

    @Override
    public List<Order> selectList(Order iOrder) {
        LambdaQueryWrapper <Order> lqw = this.lqw(iOrder);
        return iOrderMapper.selectList(lqw);

    }

    @Override
    @Transactional
    public Integer insert(Order iOrder) {
//      查询库存是否足够
        ProductDetails detailsSelect = new ProductDetails();
        detailsSelect.setId(iOrder.getProductId());
        ProductDetails details = goodsFeign.select(detailsSelect).getData().get(0);
        if (details.getInventory() < iOrder.getNum()) {
            return -1;
        }

//        下单
        iOrder.setId(IdWorker.getId());
        String userName = tokenDecode.getUserInfo().get("user_name");
        iOrder.setBuyerName(userName);
        int insert = iOrderMapper.insert(iOrder);
        if(insert != 1){
            return -2;
        }
        //删除购物车的商品
        cartFeign.delete(iOrder.getProductId().toString());
        //把发送的信息存入到task表中
        Task task=new Task();
        task.setId(IdWorker.getId());
        task.setTaskType("0");
        task.setMqExchange(RabbitConfig.ORDER_EXCHANGE);
        task.setMqRoutingkey(RabbitConfig.ORDER_QUEUE);
        task.setRequestBody(JSON.toJSONString(iOrder));
        taskMapper.insert(task);
        //发送消息
        rabbitTemplate.convertAndSend(RabbitConfig.ORDER_EXCHANGE,RabbitConfig.ORDER_QUEUE,JSON.toJSONString(task));

        return 1;

    }

    @Override
    @Transactional
    public Integer delete(String ids) {
        try {
            String[] split = ids.split(",");
            for (String id : split) {
                Order iOrder = new Order();
                iOrder.setId(Long.parseLong(id));
                iOrder.setIsDelete(1);
                iOrderMapper.updateById(iOrder);
            }
            return 1;
        }catch (Exception e){
            return -1;
        }

    }

    @Override
    public Integer update(Order iOrder) {
        return iOrderMapper.updateById(iOrder);
    }

    @Override
    public Page<Order> pageAll(Integer page, Integer size) {
        PageHelper.startPage(page,size);
        return (Page<Order>)this.selectAll();

    }

    @Override
    public Page<Order> pageList(Order iOrder, Integer page, Integer size) {
        PageHelper.startPage(page,size);
        return (Page<Order>)this.selectList(iOrder);

    }
    public LambdaQueryWrapper lqw(Order iOrder){
        LambdaQueryWrapper <Order> lqw = new LambdaQueryWrapper<>();
        if (iOrder.getId() != null){
            lqw.eq(Order::getId,iOrder.getId());
        }
        if (iOrder.getSource() != null){
            lqw.eq(Order::getSource,iOrder.getSource());
        }
        if (iOrder.getType() != null){
            lqw.eq(Order::getType,iOrder.getType());
        }
        if (iOrder.getBuyerName() != null){
            lqw.eq(Order::getBuyerName,iOrder.getBuyerName());
        }
        if (iOrder.getMoney() != null){
            lqw.eq(Order::getMoney,iOrder.getMoney());
        }
        if (iOrder.getStatus() != null){
            lqw.eq(Order::getStatus,iOrder.getStatus());
        }
        if (iOrder.getUpdateTime() != null){
            lqw.eq(Order::getUpdateTime,iOrder.getUpdateTime());
        }
        if (iOrder.getCreateTime() != null){
            lqw.eq(Order::getCreateTime,iOrder.getCreateTime());
        }
        if (iOrder.getIsDelete() != null){
            lqw.eq(Order::getIsDelete,iOrder.getIsDelete());
        }
        if (iOrder.getAddressId() != null){
            lqw.eq(Order::getAddressId,iOrder.getAddressId());
        }
        if (iOrder.getProductId() != null){
            lqw.eq(Order::getProductId,iOrder.getProductId());
        }
        if (iOrder.getNum() != null){
            lqw.eq(Order::getNum,iOrder.getNum());
        }
        if (iOrder.getTradeNo() != null){
            lqw.eq(Order::getTradeNo,iOrder.getTradeNo());
        }
        return lqw;
    }

}




