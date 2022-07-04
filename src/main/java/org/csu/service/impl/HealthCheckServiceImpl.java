package org.csu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.csu.domain.HealthCheck;
import org.csu.mapper.HealthCheckMapper;
import org.csu.vo.ResponseResult;
import org.csu.service.HealthCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class HealthCheckServiceImpl implements HealthCheckService {
    @Autowired
    private HealthCheckService healthCheckService;
    @Autowired
    private HealthCheckMapper healthCheckMapper;


    @Override
    public ResponseResult submit(HealthCheck healthCheck) {
        QueryWrapper<HealthCheck> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid", healthCheck.getUid());
        queryWrapper.eq("time", healthCheck.getTime());
        HealthCheck healthCheck1 = healthCheckMapper.selectOne(queryWrapper);

        if(healthCheck1 == null){ //不存在这一条记录时
            healthCheckMapper.insert(healthCheck);
            return new ResponseResult(200, "健康打卡提交成功");
        }
        return new ResponseResult(451, "健康打卡提交失败");
    }

    @Override
    public ResponseResult getInfo() {
        ArrayList<HealthCheck> healthChecks = (ArrayList<HealthCheck>) healthCheckMapper.selectList(null);
        return new ResponseResult(200, "健康打卡信息查询成功", healthChecks);
    }

    public ResponseResult getInfoById(long id){
        HealthCheck healthCheck = healthCheckMapper.selectById(id);
        if(healthCheck.equals(null))
            return new ResponseResult(450, "该id下无健康打卡信息");
        return new ResponseResult(200, "查询成功",healthCheck);
    }

}
