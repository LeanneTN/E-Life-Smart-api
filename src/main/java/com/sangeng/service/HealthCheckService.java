package com.sangeng.service;

import com.sangeng.domain.HealthCheck;
import com.sangeng.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;

public interface HealthCheckService {
    ResponseResult submit(HealthCheck healthCheck);

    ResponseResult getInfo();
}
