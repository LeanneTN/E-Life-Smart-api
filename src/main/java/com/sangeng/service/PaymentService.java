package com.sangeng.service;

import com.sangeng.vo.ResponseResult;

public interface PaymentService {
    ResponseResult pay();

    ResponseResult payById(Long id);

    ResponseResult getOrders();

    ResponseResult getIncome();
}
