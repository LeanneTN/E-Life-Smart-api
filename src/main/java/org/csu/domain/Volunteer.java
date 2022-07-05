package org.csu.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_volunteer")
public class Volunteer {

    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private String freeTime;
    private double totalTime;
    private String home;
}
