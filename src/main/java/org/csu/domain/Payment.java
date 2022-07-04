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
@TableName("sys_payment")
public class Payment implements Serializable {
    private static final long serialVersionUID = -40356785423868312L;

    @TableId
    private long id;
    private long fromUser;
    private long toAdmin;
    private String type;
    private double sum;
    private Date time;
}
