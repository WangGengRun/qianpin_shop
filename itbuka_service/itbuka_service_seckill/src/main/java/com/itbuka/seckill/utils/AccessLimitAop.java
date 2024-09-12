package com.itbuka.seckill.utils;

import com.alibaba.fastjson.JSON;
import com.google.common.util.concurrent.RateLimiter;
import com.itbuka.entity.Result;
import com.itbuka.entity.StatusCode;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Scope
@Aspect
public class AccessLimitAop {

    @Autowired
    private HttpServletResponse httpServletResponse;

    private RateLimiter rateLimiter = RateLimiter.create(20.0);

    @Pointcut("@annotation(com.itbuka.seckill.utils.AccessLimit)")
    public void limit(){}

    @Around("limit()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint){

        boolean flag = rateLimiter.tryAcquire();
        Object obj = null;

        try{
            if (flag){
                obj=proceedingJoinPoint.proceed();
            }else{
                String errorMessage = JSON.toJSONString(new Result(false, StatusCode.ERROR,"fail"));
                outMessage(httpServletResponse,errorMessage);
            }
        }catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return obj;

    }

    private void outMessage(HttpServletResponse response, String errorMessage) {

        ServletOutputStream outputStream = null;
        try {
            response.setContentType("application/json;charset=UTF-8");
            outputStream = response.getOutputStream();
            outputStream.write(errorMessage.getBytes("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}

