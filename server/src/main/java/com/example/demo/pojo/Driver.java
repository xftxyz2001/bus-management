package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Driver {
    private int driverID;
    private String name;
    private int age;
    private String gender;
    private String licenseNumber;
    private String phone;
    private String workLocation;
}
