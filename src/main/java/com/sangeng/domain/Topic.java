package com.sangeng.domain;

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
@TableName("sys_topic")
public class Topic implements Serializable {
    private static final long serialVersionUID = -40356785423868312L;

    @TableId
    private String id;
    private String fromUser;
    private String title;
    private String content;

    private Date lastReplyTime;
    private String lastReplyUser;
}
