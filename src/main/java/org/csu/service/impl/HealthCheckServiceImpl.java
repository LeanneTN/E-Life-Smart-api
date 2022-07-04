package org.csu.service.impl;

import org.csu.domain.HealthCheck;
import org.csu.vo.ResponseResult;
import org.csu.service.HealthCheckService;
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
