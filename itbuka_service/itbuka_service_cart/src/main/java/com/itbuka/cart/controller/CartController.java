package com.itbuka.cart.controller;


import com.itbuka.cart.domain.Cart;
import com.itbuka.cart.service.CartService;


import com.itbuka.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 购物车
 */
@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    /**
     * 新增购物车
     * @param cart
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody Cart cart) {
        try {
            cartService.add(cart);
        } catch (Exception e) {
            return Result.fail("添加失败");
        }
        return Result.ok("添加成功");
    }
    /**
     * 查询购物车
     */
    @GetMapping("/findList")
    public Result findList(){
        List<Cart> list;
        try {
            list = cartService.findList();
        } catch (Exception e) {
            return Result.fail("查询购物车失败");
        }
        return Result.ok("查询购物车成功",list);
    }
    /**
     * 修改购物车
     *
     * @param cart
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestBody Cart cart) {
        try {
            cartService.update(cart);
        } catch (Exception e) {
            return Result.fail("修改失败");
        }
        return Result.ok("修改成功");
    }
    /**
     * 删除购物车
     */
    @GetMapping("/delete")
    public Result delete(@RequestParam String ids) {
        try {
            cartService.delete(ids);
        } catch (Exception e) {
            return Result.fail("删除失败");
        }
        return Result.ok("删除成功");
    }



}

