package com.bus.management.pojo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class User {

    public static final Integer ROLE_USER = 0;
    public static final Integer ROLE_ADMIN = 1;

    @NotNull
    private Integer id;
    private String username;
    private short age;
    private short gender;
    private String phone;
    private String email;
    private Integer identify;
    @JsonIgnore
    private String password;


}
