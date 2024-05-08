package com.example.demo.service.impl;

import com.example.demo.mapper.DriverMapper;
import com.example.demo.pojo.Driver;
import com.example.demo.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverServiceImpl implements DriverService {
    @Autowired
    private DriverMapper driverMapper;

    @Override
    public List<Driver> getAllDrivers() {
        return driverMapper.getAllDrivers();
    }

    @Override
    public void addDriver(Driver driver) {
        driverMapper.addDriver(driver.getName(), driver.getAge(), driver.getGender(), driver.getLicenseNumber(), driver.getPhone(), driver.getWorkLocation());
    }

    @Override
    public Driver getDriverById(int id) {
        return driverMapper.getDriverById(id);
    }

    @Override
    public void updateDriver(int id, Driver driver) {
        driverMapper.updateDriver(id, driver);
    }

    @Override
    public void deleteDriver(int id) {
        driverMapper.deleteDriver(id);
    }
}
