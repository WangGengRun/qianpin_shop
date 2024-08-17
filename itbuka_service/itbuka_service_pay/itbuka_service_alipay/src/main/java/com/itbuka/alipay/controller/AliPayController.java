package com.itbuka.alipay.controller;

import com.itbuka.alipay.domain.AliPay;
import com.itbuka.alipay.service.AliPayService;
import com.itbuka.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletResponse;

import java.util.Map;

/**
 * 支付宝支付
 */
@RestController
@RequestMapping("/alipay")
@Transactional(rollbackFor = Exception.class)
public class AliPayController {
    private static final String CHARSET = "utf-8";

    @Autowired
    private AliPayService aliPayService;

    /**
     * h5支付
     *
     * @param aliPay
     * @param httpResponse
     * @throws Exception
     */
    @GetMapping("/pay")
    public void pay(AliPay aliPay, HttpServletResponse httpResponse) throws Exception {
        String form = aliPayService.H5Pay(aliPay);
        httpResponse.setContentType("text/html;charset=" + CHARSET);
        // 直接将完整的表单html输出到页面
        httpResponse.getWriter().write(form);
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();
    }

    /**
     * 退款
     * @param aliPay
     * @return
     */
    @RequestMapping("/refund")
    public Result refund(AliPay aliPay) {
        return Result.ok(aliPayService.refund(aliPay));
    }

    /**
     * 查看支付订单信息
     */
    @RequestMapping("/query")
    public Map queryPayment(@RequestBody AliPay aliPay) {
        return aliPayService.query(aliPay);
    }
}
