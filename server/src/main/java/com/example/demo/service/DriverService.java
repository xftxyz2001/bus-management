package com.example.demo.service;


import com.example.demo.pojo.Driver;

import java.util.List;

public interface DriverService {
    List<Driver> getAllDrivers();

    void addDriver(Driver driver);

    Driver getDriverById(int id);

    void updateDriver(int id, Driver driver);

    void deleteDriver(int id);
}
