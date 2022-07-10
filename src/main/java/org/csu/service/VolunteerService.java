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


    ResponseResult getAllVolunteers();

    ResponseResult deleteVolunteer(Long uid);

    ResponseResult updateVolunteer(Long uid, Volunteer volunteer);

    ResponseResult takeVolunteer(Long uid, VolunteerLog volunteerLog);


    ResponseResult createVolunteer(Volunteer volunteer);

    ResponseResult updateVolunteer(Volunteer volunteer);

    ResponseResult deleteVolunteerById(Long uid);

}
