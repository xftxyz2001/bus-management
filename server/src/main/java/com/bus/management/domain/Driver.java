package com.bus.management.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @TableName driver
 */
@TableName(value = "driver")
@Data
public class Driver implements Serializable {
    /**
     *
     */
    @TableId(value = "DriverID", type = IdType.AUTO)
    private Integer driverid;

    /**
     *
     */
    @TableField(value = "Name")
    private String name;

    /**
     *
     */
    @TableField(value = "Age")
    private Integer age;

    /**
     *
     */
    @TableField(value = "Gender")
    private String gender;

    /**
     *
     */
    @TableField(value = "LicenseNumber")
    private String licensenumber;

    /**
     *
     */
    @TableField(value = "Phone")
    private String phone;

    /**
     *
     */
    @TableField(value = "WorkLocation")
    private String worklocation;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}