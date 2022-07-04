package org.csu.service;

import org.csu.vo.ResponseResult;

public interface PaymentService {
    ResponseResult pay();

    ResponseResult payById(Long id);

    ResponseResult getOrders();

    ResponseResult getIncome();
}
