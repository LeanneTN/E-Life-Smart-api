package org.csu.service.impl;

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
    public ResponseResult pay() {
        return null;
    }

    @Override
    public ResponseResult payById(Long id) {
        return null;
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
