package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class message {
    private Integer id;
    private String addtime;
    private Integer userid;
    private String username;
    //留言内容
    private String content;
    //回复内容
    private String reply;
}
