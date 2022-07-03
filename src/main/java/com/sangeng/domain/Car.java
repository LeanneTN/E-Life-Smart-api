package com.sangeng.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_car")
public class Car implements Serializable {
    private static final long serialVersionUID = -40356785423868312L;

    @TableId
    private String id;
    private Long owner;
}
