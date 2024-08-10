package com.itbuka.goods.feign;

import com.itbuka.entity.Result;
import com.itbuka.goods.domain.Detail;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "goods")
public interface GoodsFeign {
    @GetMapping("/detail/findDetail")
     Result<Detail> findDetail(@RequestParam Long id);
}
