package com.itbuka.goods.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itbuka.goods.domain.Spec;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 王庚润
* @description 针对表【goods_spec(规格表)】的数据库操作Mapper
* @createDate 2024-08-10 12:48:58
* @Entity com.itbuka.goods.domain.Spec
*/
@Mapper
public interface SpecMapper extends BaseMapper<Spec> {

}




