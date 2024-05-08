package com.example.demo.controller;

import com.example.demo.pojo.Driver;
import com.example.demo.pojo.Result;
import com.example.demo.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/driver")
@Validated
public class DriverController {

    @Autowired
    private DriverService DriverService;

    @GetMapping("/getAllDrivers")
    public Result getAllDrivers() {

        List<Driver> drivers = DriverService.getAllDrivers();
        return Result.success(drivers);
    }

    @PostMapping("/addDrivers")
    public Result addDriver(Driver driver) {
        DriverService.addDriver(driver);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<Driver> getDriverById(@PathVariable int id) {
        return Result.success(DriverService.getDriverById(id));
    }

    @PutMapping("/{id}")
    public void updateDriver(@PathVariable int id, @RequestBody Driver driver) {
        DriverService.updateDriver(id, driver);
    }

    @DeleteMapping("/{id}")
    public Result deleteDriver(@PathVariable int id) {
        DriverService.deleteDriver(id);
        Driver d = DriverService.getDriverById(id);
        if (d == null) {
            return Result.success("删除成功");
        } else {
            return Result.error("出现错误");
        }
    }
}
