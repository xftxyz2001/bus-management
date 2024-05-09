package com.bus.management.service;


import com.bus.management.pojo.Driver;

import java.util.List;

public interface DriverService {
    List<Driver> getAllDrivers();

    void addDriver(Driver driver);

    Driver getDriverById(int id);

    void updateDriver(Driver driver);

    void deleteDriver(int id);

    List<Driver> getByDriverName(String driverName);
}
