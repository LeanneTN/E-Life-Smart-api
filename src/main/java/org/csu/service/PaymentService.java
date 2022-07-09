package org.csu.service;

import org.csu.domain.Payment;
import org.csu.vo.ResponseResult;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface PaymentService {
    ResponseResult pay(Long id);

    ResponseResult payById(Long id, Payment payment);

    ResponseResult getOrders(Long id);

    ResponseResult getIncome();

    ResponseResult getToPaid(HttpServletRequest req);

    ResponseResult payChecked(List<Payment> paymentList);
}
