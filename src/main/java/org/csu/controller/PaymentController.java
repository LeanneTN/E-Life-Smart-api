package org.csu.controller;

import org.csu.vo.ResponseResult;
import org.csu.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment/")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    //支付所有
    @PostMapping("/pay")
    public ResponseResult pay(){
        return paymentService.pay();
    }

    //支付某条缴费单
    @PostMapping("/pay/{id}")
    public ResponseResult payById(@PathVariable("id") Long id){
        return paymentService.payById(id);
    }

    //获取某个用户的所有支付的订单，前端可以进行统计信息的展示(用户)
    @GetMapping("/orders")
    public ResponseResult getOrders(){
        return paymentService.getOrders();
    }

    //获取全部订单(后台管理使用)
    @GetMapping("/income")
    @PreAuthorize("hasAuthority('system:payment:income')")
    public ResponseResult getIncome(){
        return paymentService.getIncome();
    }
}
