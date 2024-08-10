package com.itbuka.goods.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itbuka.goods.domain.ProductDetails;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* @author 王庚润
* @description 针对表【goods_product_details(商品详情表)】的数据库操作Mapper
* @createDate 2024-08-10 12:18:23
* @Entity com.itbuka.goods.domain.ProductDetails
*/
@Mapper
public interface ProductDetailsMapper extends BaseMapper<ProductDetails> {

    Integer reduceStock(@Param("productId") Long productId, @Param("num") Integer num);
}




