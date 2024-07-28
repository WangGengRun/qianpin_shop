package com.itbuka.goods.mapper;


import com.itbuka.goods.domain.Brand;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author Administrator
 * @description 针对表【goods_brand(品牌表)】的数据库操作Mapper
 * @createDate 2024-07-26 11:51:03
 * @Entity generator.domain.Brand
 */
@Mapper
public interface BrandMapper extends BaseMapper<Brand> {

}