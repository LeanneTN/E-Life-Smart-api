package org.csu.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_parking")
public class Parking implements Serializable {
    private static final long serialVersionUID = -40356785423868312L;

    public static final double unitPrice = 3.0;

    @TableId(type = IdType.AUTO)
    private Long id;
    private String carNum;
    private String parkingNum;
    private Date start;
    private Date end;
    private double totalPrice;
    private boolean isRegistered;
}
