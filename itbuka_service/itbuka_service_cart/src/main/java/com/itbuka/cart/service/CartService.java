package com.itbuka.cart.service;

import com.itbuka.cart.domain.Cart;

import java.util.List;

public interface CartService {
    //添加
    void add(Cart cart);
    //查询
    List<Cart> findList();
    //修改
    void update(Cart cart);
    //删除
    void delete(String ids);


}

