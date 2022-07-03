package com.sangeng.service.impl;

import com.sangeng.vo.ResponseResult;
import com.sangeng.domain.VolunteerLog;
import com.sangeng.mapper.VolunteerLogMapper;
import com.sangeng.mapper.VolunteerMapper;
import com.sangeng.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VolunteerServiceImpl implements VolunteerService {
    @Autowired
    private VolunteerMapper volunteerMapper;
    @Autowired
    private VolunteerLogMapper volunteerLogMapper;

    @Override
    public ResponseResult applyForVolunteer() {
        return null;
    }

    @Override
    public ResponseResult getMyLogs() {
        return null;
    }

    @Override
    public ResponseResult insertVolunteerLog(VolunteerLog volunteerLog) {
        return null;
    }

    @Override
    public ResponseResult getLogs() {
        return null;
    }
}
