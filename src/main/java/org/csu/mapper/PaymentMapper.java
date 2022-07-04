package org.csu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.csu.domain.Payment;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentMapper extends BaseMapper<Payment> {
}
