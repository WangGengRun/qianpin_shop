package com.itbuka.oauth.mapper;

import com.itbuka.oauth.domain.SysUser;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface UserMapper {
    @Select("select * from sys_user where username=#{username}")
    public SysUser findByUsername(String username);
}

