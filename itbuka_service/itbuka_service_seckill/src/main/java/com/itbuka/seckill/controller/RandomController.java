package com.itbuka.seckill.controller;
import com.itbuka.seckill.service.impl.RandomServiceImpl;
import com.itbuka.utils.TokenDecode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 生成随机数
 */
@RestController
@RequestMapping("/random")
public class RandomController {
    @Autowired
    private RandomServiceImpl randomService;
    @Autowired
    private TokenDecode tokenDecode;

    /**
     * 获取随机数
     * @return
     */
    @RequestMapping("/getRandom")
    public String getRandom() {
        String userName = tokenDecode.getUserInfo().get("user_name");
        String randomString = randomService.getRandomString(userName);
        return randomString;
    }
}

