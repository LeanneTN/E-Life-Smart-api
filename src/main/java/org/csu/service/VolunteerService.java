package org.csu.service;

import org.csu.domain.Volunteer;
import org.csu.vo.ResponseResult;
import org.csu.domain.VolunteerLog;

public interface VolunteerService {
    ResponseResult applyForVolunteer(Volunteer volunteer, long uid);

    ResponseResult getMyLogs(long id);

    ResponseResult insertVolunteerLog(VolunteerLog volunteerLog);

    ResponseResult getLogs();

    ResponseResult getLogsGroupById();
}
