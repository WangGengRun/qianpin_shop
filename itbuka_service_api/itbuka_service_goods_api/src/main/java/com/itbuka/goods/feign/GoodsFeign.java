package com.itbuka.goods.feign;

import com.itbuka.entity.Result;
import com.itbuka.goods.domain.Detail;
import com.itbuka.goods.domain.ProductDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "goods",url = "127.0.0.1:9011")
public interface GoodsFeign {
    @GetMapping("/detail/findDetail")
     Result<Detail> findDetail(@RequestParam Long id);
    /**
     * 条件查询商品详情表
     * @param iProductDetails
     * @return
     */
    @PostMapping("/ProductDetails/selectList")
    Result<List<ProductDetails>> select(@RequestBody ProductDetails iProductDetails);
    /**
     * 减库存
     * @param productId ，num
     * @return
     */
    @GetMapping("/reduceStock")
    public Result reduceStock(@RequestParam Long productId,@RequestParam Integer num);

}

