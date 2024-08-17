package com.itbuka.order.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itbuka.order.domain.Order;

import java.util.List;
import java.util.Map;


/**
* @author 王庚润
* @description 针对表【itbuka_order】的数据库操作Service
* @createDate 2024-08-13 10:08:18
*/
public interface OrderService  {
    /**
     * 查询全部
     * @param
     * @return
     */
    List<Order> selectAll();

    /**
     * 条件查询
     * @param iOrder
     * @return
     */
    List<Order> selectList(Order iOrder);

    /**
     * 插入一条数据
     * @param iOrder
     * @return
     */
    Integer insert(Order iOrder);

    /**
     * 根据id删除数据
     * @param ids
     * @return
     */
    Integer delete(String ids);

    /**
     * 更新数据
     * @param iOrder
     * @return
     */
    Integer update(Order iOrder);

    /***
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    Page<Order> pageAll(Integer page, Integer size);

    /***
     * 多条件分页查询
     * @param iOrder
     * @param page
     * @param size
     * @return
     */
    Page<Order> pageList(Order iOrder, Integer page, Integer size);

    /**
     * 购物车下单
     * @param map
     * @return
     */
    Integer addCart(Map map);
    /**
     * 清空购物车
     * @param map
     * @return
     */
    Integer clearCart(Map map);

}
