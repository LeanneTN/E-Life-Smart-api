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
@TableName("sys_repair")
public class Repair implements Serializable {
    private static final long serialVersionUID = -40356785423868312L;

    //基本信息
    @TableId
    private Long id;
    private Long fromUser;
    private String type;
    private Date start;
    private Date end;

    private String img; //可有可无
    private String description;
    private String status;  //报修的状态（未解决/正在解决/已解决）
    private Long repairerId;
}
