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

    ResponseResult getVolunteerTasks(long uid, String freeTime);

    ResponseResult getVolunteer(Long uid);

    ResponseResult deleteVolunteer(Long uid);

    ResponseResult updateVolunteer(Long uid, Volunteer volunteer);
}
