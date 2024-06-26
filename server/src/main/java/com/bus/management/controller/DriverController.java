package com.bus.management.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.bus.management.domain.Driver;
import com.bus.management.result.Result;
import com.bus.management.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/driver")
@RequiredArgsConstructor
public class DriverController {


    private final DriverService driverService;

    @GetMapping("/getAllDrivers")
    public Result<?> getAllDrivers() {

        List<Driver> drivers = driverService.list();
        return Result.success(drivers);
    }

    @PostMapping("/addDriver")
    public Result<?> addDriver(@RequestBody Driver driver) {
        driverService.save(driver);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<?> getDriverById(@PathVariable("id") Integer id) {
        Driver driver = driverService.getById(id);
        return Result.success(driver);
    }

    @GetMapping("/getByDriverName")
    public Result<?> getByDriverName(@RequestParam("driverName") String driverName) {
        List<Driver> drivers = driverService.list(Wrappers.<Driver>lambdaQuery().like(Driver::getName, driverName));
        return Result.success(drivers);
    }

    @PutMapping("/update")
    public Result<?> updateDriver(@RequestBody Driver driver) {
        driverService.updateById(driver);
        return Result.success("更新成功");
    }

    @DeleteMapping("/{id}")
    public Result<?> deleteDriver(@PathVariable("id") Integer id) {
        driverService.removeById(id);
        return Result.success("删除成功");
    }
}
