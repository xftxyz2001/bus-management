package com.bus.management.vo.req;

import lombok.Data;

@Data
public class RegisterReq {

    private String username;
    private String password;
    private Integer age;
    private Integer gender;
    private String phone;
}
