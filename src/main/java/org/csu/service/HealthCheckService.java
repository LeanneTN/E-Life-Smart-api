package org.csu.service;

import org.csu.domain.HealthCheck;
import org.csu.vo.ResponseResult;

public interface HealthCheckService {
    ResponseResult submit(HealthCheck healthCheck);

    ResponseResult getInfo();

    ResponseResult getInfoById(long id);
}
