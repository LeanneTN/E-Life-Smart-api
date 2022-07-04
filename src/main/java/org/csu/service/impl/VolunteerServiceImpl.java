package org.csu.service.impl;

import org.csu.vo.ResponseResult;
import org.csu.domain.VolunteerLog;
import org.csu.mapper.VolunteerLogMapper;
import org.csu.mapper.VolunteerMapper;
import org.csu.service.VolunteerService;
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
