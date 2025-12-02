package com.itbuka.order.feign;

import com.itbuka.entity.Result;
import com.itbuka.order.domain.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
@FeignClient(name = "order")
public interface OrderFeign {
    /**
     * 条件查询
     * @param iOrder
     * @return
     */
    @PostMapping("/Order/selectList")
     Result<List<Order>> select(@RequestBody Order iOrder);
}
