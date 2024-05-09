package com.bus.management.controller;

import com.bus.management.pojo.Driver;
import com.bus.management.result.Result;
import com.bus.management.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/driver")
@Validated
public class DriverController {

    @Autowired
    private DriverService driverService;

    @GetMapping("/getAllDrivers")
    public Result getAllDrivers() {

        List<Driver> drivers = driverService.getAllDrivers();
        return Result.success(drivers);
    }

    @PostMapping("/addDriver")
    public Result addDriver(@RequestBody Driver driver) {
        driverService.addDriver(driver);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<Driver> getDriverById(@PathVariable int id) {
        return Result.success(driverService.getDriverById(id));
    }

    @GetMapping("/getByDriverName")
    public Result<List<Driver>> getByDriverName(String driverName) {
        return Result.success(driverService.getByDriverName(driverName));
    }

    @PutMapping("/update")
    public Result<String> updateDriver(@RequestBody Driver driver) {
        driverService.updateDriver(driver);
        return Result.success("更新成功");
    }

    @DeleteMapping("/{id}")
    public Result deleteDriver(@PathVariable int id) {
        driverService.deleteDriver(id);
        Driver d = driverService.getDriverById(id);
        if (d == null) {
            return Result.success("删除成功");
        } else {
            return Result.error("出现错误");
        }
    }
}
