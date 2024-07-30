package com.itbuka.user.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itbuka.goods.domain.User;


import java.util.List;

/**
* @author 王庚润
* @description 针对表【user(用户表)】的数据库操作Service
* @createDate 2024-07-30 10:03:38
*/
public interface UserService extends IService<User> {

    List<User> selectAll();

    List<User> selectList(User iUser);

    Integer insert(User iUser);

    int delete(String ids);

    int update(User iUser);

    Page<User> pageAll(Integer page, Integer size);

    Page<User> pageList(User iUser, Integer page, Integer size);

    int status(Long id, Integer status);

    void resetPwd(List<Long> ids);

    String login(String username, String password);
}
