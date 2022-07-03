package com.sangeng.service;

import com.sangeng.vo.ResponseResult;
import com.sangeng.domain.VolunteerLog;

public interface VolunteerService {
    ResponseResult applyForVolunteer();

    ResponseResult getMyLogs();

    ResponseResult insertVolunteerLog(VolunteerLog volunteerLog);

    ResponseResult getLogs();
}
