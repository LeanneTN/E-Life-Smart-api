package com.sangeng.service.impl;

import com.sangeng.domain.HealthCheck;
import com.sangeng.vo.ResponseResult;
import com.sangeng.service.HealthCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HealthCheckServiceImpl implements HealthCheckService {
    @Autowired
    private HealthCheckService healthCheckService;

    @Override
    public ResponseResult submit(HealthCheck healthCheck) {
        return null;
    }

    @Override
    public ResponseResult getInfo() {
        return null;
    }
}
