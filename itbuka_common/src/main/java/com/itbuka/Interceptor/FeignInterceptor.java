package com.itbuka.Interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
public class FeignInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        //获取请求头
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
            if (request != null) {
                //从请求头中获取token
                String header = request.getHeader("authorization");
                if (null!= header && !header.equals("")) {
                    //转发token
                    if (header.contains("Bearer")) {
                        template.header("Authorization",header);
                    }else {
                        template.header("Authorization","Bearer "+header);
                    }

                }
            }

        }
    }
}

