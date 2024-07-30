package com.itbuka.user.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itbuka.goods.domain.User;
import org.apache.ibatis.annotations.Mapper;


/**
* @author 王庚润
* @description 针对表【user(用户表)】的数据库操作Mapper
* @createDate 2024-07-30 10:03:38
* @Entity com.itbuka.goods.domain.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




