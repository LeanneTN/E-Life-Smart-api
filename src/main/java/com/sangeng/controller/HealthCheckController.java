package com.sangeng.controller;

import com.sangeng.domain.HealthCheck;
import com.sangeng.vo.ResponseResult;
import com.sangeng.service.HealthCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health/")
public class HealthCheckController {
    @Autowired
    private HealthCheckService healthCheckService;

    @PostMapping("/submit")
    public ResponseResult submit(@RequestBody HealthCheck healthCheck){
        return healthCheckService.submit(healthCheck);
    }

    //查看打卡情况
    @PostMapping("/info")
    @PreAuthorize("hasAuthority('system:health:info')")
    public ResponseResult getInfo(){
        return healthCheckService.getInfo();
    }
}
