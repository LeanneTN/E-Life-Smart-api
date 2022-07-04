package org.csu.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_health_check")
public class HealthCheck implements Serializable {
    private static final long serialVersionUID = -40356785423868312L;

    @TableId
    private Long id;
    private Long uid;
    private double temp;
    private String location;
    private Date time;
    private String otherInfo;
}
