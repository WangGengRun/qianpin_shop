package com.itbuka.goods.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itbuka.goods.domain.Product;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 王庚润
* @description 针对表【goods_product(商品表)】的数据库操作Mapper
* @createDate 2024-07-31 14:22:43
* @Entity com.itbuka.goods.domain.Product
*/
@Mapper
public interface ProductMapper extends BaseMapper<Product> {

}




