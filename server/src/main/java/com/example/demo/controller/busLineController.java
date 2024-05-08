package com.example.demo.controller;

import com.example.demo.pojo.Result;
import com.example.demo.pojo.busLine;
import com.example.demo.service.busLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bus")
@Validated
public class busLineController {
    @Autowired
    private busLineService busLineService;

    @PostMapping("/add")
    public Result add(@RequestBody busLine bus) {
        if (bus == null) {
            return Result.error("参数错误");
        }
        if (bus.getBusid() == null) {
            return Result.error("请输入公交线路");
        }

        busLine b = busLineService.findBybusid(bus.getBusid());
        if (b == null) {
            busLineService.registerbus(bus);
            return Result.success();
        } else {
            return Result.error("该公交车已存在");
        }
    }

    @GetMapping("/getAll")
    public Result getAll() {
        List<busLine> all = busLineService.getAll();
        return Result.success(all);
    }

    @GetMapping("/getByBusid")
    public Result getByBusid(String busid) {
        busLine bus = busLineService.findBybusid(busid);
        return Result.success(bus);
    }

    @PostMapping("/update")
    public Result update(@RequestBody busLine bus) {
        busLineService.update(bus);
        return Result.success();
    }

    @DeleteMapping("/delete")
    public Result delete(String id) {
        busLineService.delete(id);
        return Result.success();
    }

}
