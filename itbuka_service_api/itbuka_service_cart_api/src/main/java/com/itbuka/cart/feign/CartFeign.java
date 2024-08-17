package com.itbuka.cart.feign;

import com.itbuka.cart.domain.Cart;
import com.itbuka.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "cart")
public interface CartFeign {

/**
 * 删除购物车
 */
@GetMapping("/cart/delete")
Result delete(@RequestParam String ids);
    /**
     * 查询购物车
     */
    @GetMapping("/findList")
   Result<List<Cart>> findList();

}
