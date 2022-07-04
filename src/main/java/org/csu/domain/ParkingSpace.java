package org.csu.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_parking_space")
public class ParkingSpace implements Serializable {
    private static final long serialVersionUID = -40356785423868312L;

    @TableId
    private Long id;
    private String carNum;
    private String type;
}
