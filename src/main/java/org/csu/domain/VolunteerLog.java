package org.csu.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_volunteer_log")
public class VolunteerLog {
    @TableId
    private Long id;
    private Long volunteerId;
    private String event;
    private double totalTime;
}
