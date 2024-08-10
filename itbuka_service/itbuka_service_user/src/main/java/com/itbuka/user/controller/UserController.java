package com.itbuka.user.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itbuka.entity.PageResult;
import com.itbuka.entity.Result;
import com.itbuka.goods.domain.User;
import com.itbuka.user.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户表
 */
@RestController
@RequestMapping("/User")
public class UserController {
    @Autowired
    private UserService iUserService;


    /**
     * 查询全部用户表
     * @param
     * @return
     */
    @GetMapping("/selectAll")
    public Result<List<User>> selectAll(){
        return Result.ok("查询成功",iUserService.selectAll());
    }


    /**
     * 条件查询用户表
     * @param iUser
     * @return
     */
    @PostMapping("/selectList")
    public Result< List<User> > select(@RequestBody User iUser){
        return Result.ok("条件查询成功",iUserService.selectList(iUser));
    }

    /**
     * 新增用户表
     * @param iUser
     * @return
     */
    @PostMapping("/insert")
    public Result insert(@RequestBody User iUser){
        Integer insert = iUserService.insert(iUser);
        if(insert == -2){
            return Result.fail("用户名已经存在");
        }
        return Result.ok("新增成功");
    }


    /**
     * 删除用户表
     * @param ids
     * @return
     */
    @GetMapping("/delete")
    public Result delete(@RequestParam String ids){
        iUserService.delete(ids);
        return Result.ok("删除成功");
    }


    /**
     * 修改用户表
     * @param iUser
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestBody User iUser){
        iUserService.update(iUser);
        return Result.ok("修改成功");
    }

    /**
     * 分页搜索实现用户表
     * @param page 默认值1
     * @param size 默认值10
     * @return
     */
    @GetMapping(value = "/pageAll" )
    public Result pageAll(@RequestParam(defaultValue = "1")  Integer page, @RequestParam(defaultValue = "10") Integer size){
        Page<User> pageList = iUserService.pageAll(page, size);
        PageResult pageResult=new PageResult(pageList.getTotal(),pageList.getRecords());
        return Result.ok("查询成功",pageResult);
    }

    /**
     * 分页条件搜索实现用户表
     * @param iUser
     * @param page
     * @param size
     * @return
     */
    @GetMapping(value = "/pageList" )
    public Result pageList(@RequestBody User iUser,@RequestParam(defaultValue = "1")  Integer page, @RequestParam(defaultValue = "10") Integer size){
        Page<User> pageList = iUserService.pageList(iUser,page, size);
        PageResult pageResult=new PageResult(pageList.getTotal(),pageList.getRecords());
        return Result.ok("查询成功",pageResult);
    }

    /**
     * 启动、
     * 停用
     * @param id
     * @param status
     * @return
     */
    @GetMapping("/status")
    public Result updateStatus(@RequestParam Long id,@RequestParam Integer status){
        iUserService.status(id,status);
        return Result.ok("修改成功");
    }

    /**
     * 重置密码
     */
    @PostMapping("/reset")
    public Result reset(@RequestBody List<Long> ids){
        iUserService.resetPwd(ids);
        return Result.ok("修改成功");
    }

    /**
     * 登录
     *
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        String login = iUserService.login(user.getUsername(), user.getPassword());
        if (login == "-2") {
            return Result.fail("用户名或密码错误");
        } else if (login == "-1") {
            return Result.fail("该用户不存在");
        } else if (login == "-3") {
            return Result.fail("该用户已停用");
        }
        return  Result.ok("登录成功");
    }

}
