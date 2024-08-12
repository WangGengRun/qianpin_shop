package com.itbuka.goods.feign;

import com.itbuka.entity.Result;
import com.itbuka.goods.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "user")
public interface UserFeign {
    /**
     * 条件查询用户表
     * @param iUser
     * @return
     */
    @PostMapping("/User/selectList")
    Result<List<User>> select(@RequestBody User iUser);

}
