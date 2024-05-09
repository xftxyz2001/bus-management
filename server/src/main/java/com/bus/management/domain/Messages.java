package com.bus.management.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName messages
 */
@TableName(value ="messages")
@Data
public class Messages implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 创建时间
     */
    @TableField(value = "addtime")
    private Date addtime;

    /**
     * 留言人id
     */
    @TableField(value = "userid")
    private Long userid;

    /**
     * 用户名
     */
    @TableField(value = "username")
    private String username;

    /**
     * 留言内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * 回复内容
     */
    @TableField(value = "reply")
    private Long reply;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}