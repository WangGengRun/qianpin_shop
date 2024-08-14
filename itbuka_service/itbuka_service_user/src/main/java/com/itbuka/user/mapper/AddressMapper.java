package com.itbuka.user.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itbuka.goods.domain.Address;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 王庚润
* @description 针对表【itbuka_address】的数据库操作Mapper
* @createDate 2024-08-13 10:47:38
* @Entity com.itbuka.goods.domain.Address
*/
@Mapper
public interface AddressMapper extends BaseMapper<Address> {

}




