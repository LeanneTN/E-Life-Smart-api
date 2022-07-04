package org.csu.service;

import org.csu.vo.ResponseResult;
import org.csu.domain.VolunteerLog;

public interface VolunteerService {
    ResponseResult applyForVolunteer();

    ResponseResult getMyLogs();

    ResponseResult insertVolunteerLog(VolunteerLog volunteerLog);

    ResponseResult getLogs();
}
