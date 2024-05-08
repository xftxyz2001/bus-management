package com.example.demo.pojo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class User {
    @NotNull
    private Integer id;
    private String username;
    private short age;
    private short gender;
    private String phone;
    private String email;
    @JsonIgnore
    private String password;



}
