package com.itbuka.order.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itbuka.order.domain.Order;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 王庚润
* @description 针对表【itbuka_order】的数据库操作Mapper
* @createDate 2024-08-13 10:08:18
* @Entity com.itbuka.order.domain.Order
*/
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

}




