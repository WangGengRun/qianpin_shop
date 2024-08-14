package com.itbuka.cart.service.impl;

import com.itbuka.cart.domain.Cart;
import com.itbuka.cart.service.CartService;
import com.itbuka.utils.TokenDecode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private TokenDecode tokenDecode;

    @Override
    public void add(Cart cart) {
        //获取用户名
        String username = tokenDecode.getUserInfo().get("user_name");
        //查询购物车
        Cart c = (Cart)redisTemplate.boundHashOps("Cart"+username).get(cart.getProductId());
        if (c != null) {
            //不为空原数据上添加
            c.setNum(cart.getNum()+c.getNum());
            c.setMoney(cart.getUnitPrice().multiply(BigDecimal.valueOf(c.getNum())));
            redisTemplate.boundHashOps("Cart"+username).put(c.getProductId(),c);
        }else {
            //为空新增
            redisTemplate.boundHashOps("Cart"+username).put(cart.getProductId(),cart);
        }
    }

    @Override
    public List<Cart> findList() {
        //获取用户名
        String username = tokenDecode.getUserInfo().get("user_name");
        List values = redisTemplate.boundHashOps("Cart"+username).values();
        return values;
    }

    @Override
    public void update(Cart cart) {
        //获取用户名
        String username = tokenDecode.getUserInfo().get("user_name");
        redisTemplate.boundHashOps("Cart"+username).put(cart.getProductId(),cart);

    }

    @Override
    public void delete(String ids) {
        String[] split = ids.split(",");
        //获取用户名
        String username = tokenDecode.getUserInfo().get("user_name");
        for (String id : split) {
            redisTemplate.boundHashOps("Cart"+username).delete(Long.valueOf(id));
        }

    }
}


