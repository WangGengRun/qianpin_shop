package com.itbuka.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.itbuka.goods.domain.User;
import com.itbuka.user.mapper.UserMapper;
import com.itbuka.user.service.UserService;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author 王庚润
* @description 针对表【user(用户表)】的数据库操作Service实现
* @createDate 2024-07-30 10:03:38
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {
    @Autowired
private UserMapper iUserMapper;

    public LambdaQueryWrapper lqw(User iUser){
        LambdaQueryWrapper <User> lqw = new LambdaQueryWrapper<>();
        if (iUser.getId()!= null){
            lqw.eq(User::getId,iUser.getId());
        }
        if (iUser.getUsername() != null){
            lqw.eq(User::getUsername,iUser.getUsername());
        }
        if (iUser.getAvatar() != null){
            lqw.eq(User::getAvatar,iUser.getAvatar());
        }
        if (iUser.getPassword() != null){
            lqw.eq(User::getPassword,iUser.getPassword());
        }
        if (iUser.getPhoneNum() != null){
            lqw.eq(User::getPhoneNum,iUser.getPhoneNum());
        }
        if (iUser.getEmail() != null){
            lqw.eq(User::getEmail,iUser.getEmail());
        }
        if (iUser.getNickname() != null){
            lqw.eq(User::getNickname,iUser.getNickname());
        }
        if (iUser.getDepartment() != null){
            lqw.eq(User::getDepartment,iUser.getDepartment());
        }
        if (iUser.getRole() != null){
            lqw.eq(User::getRole,iUser.getRole());
        }
        if (iUser.getStatus() != null){
            lqw.eq(User::getStatus,iUser.getStatus());
        }
        if (iUser.getIsDelete() != null){
            lqw.eq(User::getIsDelete,iUser.getIsDelete());
        }
        if (iUser.getCreateTime() != null){
            lqw.eq(User::getCreateTime,iUser.getCreateTime());
        }
        if (iUser.getUpdateTime() != null){
            lqw.eq(User::getUpdateTime,iUser.getUpdateTime());
        }
        return lqw;
    }

    @Override
    public List<User> selectAll() {
        LambdaQueryWrapper <User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getIsDelete,0);
        return iUserMapper.selectList(lqw);
    }

    @Override
    public List<User> selectList(User iUser) {
        LambdaQueryWrapper <User> lqw = this.lqw(iUser);
        return iUserMapper.selectList(lqw);
    }

    @Override
    public Integer insert(User iUser) {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getUsername,iUser.getUsername());
        List<User> list = iUserMapper.selectList(lqw);
        if (list.size() > 0) {
            return -2;
        }
        String hashpw = BCrypt.hashpw(iUser.getPassword(), BCrypt.gensalt());
        iUser.setPassword(hashpw);
        return iUserMapper.insert(iUser);
    }

    @Override
    @Transactional
    public int delete(String ids) {
        try {
            String[] split = ids.split(",");
            for (String id : split) {
                User iUser = new User();
                iUser.setId(Long.parseLong(id));
                iUser.setIsDelete(1);
                iUserMapper.updateById(iUser);
            }
            return 1;
        }catch (Exception e){
            return -1;
        }
    }

    @Override
    public int update(User iUser) {
        return iUserMapper.updateById(iUser);
    }

    @Override
    public Page<User> pageAll(Integer page, Integer size) {
        PageHelper.startPage(page,size);
        return (Page<User>)this.selectAll();
    }

    @Override
    public Page<User> pageList(User iUser, Integer page, Integer size) {
        PageHelper.startPage(page,size);
        return (Page<User>)this.selectList(iUser);
    }

    @Override
    public int status(Long id, Integer status) {
        User user = new User();
        user.setId(id);
        user.setStatus(status);
        return iUserMapper.updateById(user);
    }

    @Override
    @Transactional
    public void resetPwd(List<Long> ids) {
        for (Long id : ids) {
            String hashpw = BCrypt.hashpw("123456", BCrypt.gensalt());
            User user = new User();
            user.setId(id);
            user.setPassword(hashpw);
            int i = iUserMapper.updateById(user);
            if (i != 1) {
                throw new RuntimeException("重置密码失败");
            }
        }
    }

    @Override
    public String login(String username, String password) {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getUsername,username);
        User user = iUserMapper.selectOne(lqw);
        if (user == null) {
            return "-1";
        }
        if (!BCrypt.checkpw(password,user.getPassword())) {
            return "-2";
        }
        if (user.getStatus() != 0 ){
            return "-3";
        }
        //生产token
        Date date = new Date();
        Map map = new HashMap();
        map.put("username",user.getUsername());
        map.put("id",user.getId());
        map.put("role",user.getRole());
        JwtBuilder itbuka = Jwts.builder().setId(IdWorker.get32UUID())
                .setClaims(map)
                .setIssuedAt(date)
                .setExpiration(new Date(date.getTime() + 1000 * 60 * 60 * 24 * 7))
                .signWith(SignatureAlgorithm.HS256, "itbuka");
        String token = itbuka.compact();
        return token;
    }
}




