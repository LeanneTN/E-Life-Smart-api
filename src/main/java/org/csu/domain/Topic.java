package org.csu.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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

    @TableId(type = IdType.AUTO)
    private Long id;
    private Long fromUser;
    private String title;
    private String content;
    private boolean isReported;
    private int response;

    private String img;

    private Date createTime;
    private Date lastReplyTime;
    private String lastReplyUser;
}
