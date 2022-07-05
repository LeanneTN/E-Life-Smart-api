package org.csu.service.impl;

import org.csu.domain.Payment;
import org.csu.vo.ResponseCode;
import org.csu.vo.ResponseResult;
import org.csu.mapper.PaymentMapper;
import org.csu.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentMapper paymentMapper;

    @Override
    public ResponseResult pay(Long id) {
        return null;
    }

    @Override
    public ResponseResult payById(Long id, Payment payment) {
        Payment payment1 = paymentMapper.selectById(id);
        if(payment1!= null){
            return new ResponseResult(ResponseCode.PAYMENT_EXIST.getCode(), "订单已存在", payment1);
        }
        paymentMapper.insert(payment);
        return new ResponseResult(ResponseCode.SUCCESS.getCode(), "支付成功", payment);
    }

    @Override
    public ResponseResult getOrders() {
        return null;
    }

    @Override
    public ResponseResult getIncome() {
        return null;
    }
}
