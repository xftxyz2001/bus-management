package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class board {
    private Integer id;
    private String title;
    private String content;
    private Date createTime;
    private Date updateTime;
}
