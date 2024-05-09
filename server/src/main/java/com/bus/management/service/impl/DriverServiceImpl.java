package com.bus.management.service.impl;

import com.bus.management.mapper.DriverMapper;
import com.bus.management.pojo.Driver;
import com.bus.management.service.DriverService;
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
    public void updateDriver(Driver driver) {
        driverMapper.updateDriver(driver);
    }

    @Override
    public void deleteDriver(int id) {
        driverMapper.deleteDriver(id);
    }

    @Override
    public List<Driver> getByDriverName(String driverName) {
        return driverMapper.getByDriverName(driverName);
    }
}
