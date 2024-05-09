package com.bus.management.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @TableName busline
 */
@TableName(value = "busline")
@Data
public class Busline implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 城市
     */
    @TableField(value = "city")
    private String city;

    /**
     * 始发站
     */
    @TableField(value = "startstation")
    private String startstation;

    /**
     * 终点站
     */
    @TableField(value = "endstation")
    private String endstation;

    /**
     * 途径站点
     */
    @TableField(value = "routesite")
    private String routesite;

    /**
     * 公交车号
     */
    @TableField(value = "busid")
    private String busid;

    /**
     * 运行时间
     */
    @TableField(value = "runtime")
    private String runtime;

    /**
     * 参考票价
     */
    @TableField(value = "price")
    private String price;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}