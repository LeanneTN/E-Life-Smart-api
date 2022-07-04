package org.csu.domain;

import com.baomidou.mybatisplus.annotation.TableField;
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

    @MppMultiId
    @TableField(value = "car_num")
    private String carNum;
    @MppMultiId
    @TableField(value = "parking_num")
    private String parkingNum;
    private Date start;
    private Date end;

    private double totalPrice;
}
