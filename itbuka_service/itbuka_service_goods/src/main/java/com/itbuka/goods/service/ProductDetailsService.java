package com.itbuka.goods.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itbuka.goods.domain.ProductDetails;

import java.util.List;

/**
* @author 王庚润
* @description 针对表【goods_product_details(商品详情表)】的数据库操作Service
* @createDate 2024-08-10 12:18:23
*/
public interface ProductDetailsService extends IService<ProductDetails> {

    List<ProductDetails> selectAll();

    List<ProductDetails> selectList(ProductDetails iProductDetails);

    int insert(ProductDetails iProductDetails);

    int delete(String ids);

    int update(ProductDetails iProductDetails);

    Page<ProductDetails> pageAll(Integer page, Integer size);

    Page<ProductDetails> pageList(ProductDetails iProductDetails, Integer page, Integer size);

    Integer reduceStock(Long productId, Integer num);
}
