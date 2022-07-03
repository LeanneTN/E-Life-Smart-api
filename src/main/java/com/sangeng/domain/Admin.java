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
@TableName("sys_admin")
public class Admin implements Serializable {
    private static final long serialVersionUID = -40356785423868312L;

    //基本信息
    @TableId
    private Long aid;
    private String avatar;
    private String password;
}
