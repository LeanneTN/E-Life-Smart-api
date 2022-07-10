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
@TableName("sys_comment")
public class Comment implements Serializable {
    private static final long serialVersionUID = -40356785423868312L;
    //基本信息
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long fromUser;
    //private Long toUser;
    private Long toId;
    private int type;   //分为三种类型，回复帖子的，回复楼主的，回复回复的
    private boolean isReported;
    private int response;   //回帖量，默认为0
    private boolean isLandlord; //是否为楼主，默认不是

    private String content;
    private Date time;  //回复时间
}
