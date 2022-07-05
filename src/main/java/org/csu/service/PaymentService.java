package org.csu.service;

import org.csu.domain.Payment;
import org.csu.vo.ResponseResult;

public interface PaymentService {
    ResponseResult pay(Long id);

    ResponseResult payById(Long id, Payment payment);

    ResponseResult getOrders();

    ResponseResult getIncome();
}
