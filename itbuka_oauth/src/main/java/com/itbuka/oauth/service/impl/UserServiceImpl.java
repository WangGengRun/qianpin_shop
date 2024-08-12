package com.itbuka.oauth.service.impl;

import com.itbuka.goods.domain.User;
import com.itbuka.goods.feign.UserFeign;
import com.itbuka.oauth.domain.SysUser;
import com.itbuka.oauth.mapper.UserMapper;
import com.itbuka.oauth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
//    @Autowired
//    private UserMapper userMapper;
    @Autowired
    private UserFeign userFeign;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       try{
           User userselect=new User();
           userselect.setUsername(username);
           User user = userFeign.select(userselect).getData().get(0);
           SysUser sysUser=new SysUser();
           sysUser.setId(user.getId());
           sysUser.setUsername(user.getUsername());
           sysUser.setPassword(user.getPassword());
           sysUser.setRoles(user.getRole());
           return sysUser;
       }catch (Exception e){
           return null;
       }
    }
}
