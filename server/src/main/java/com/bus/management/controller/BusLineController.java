package com.bus.management.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.bus.management.domain.Busline;
import com.bus.management.result.Result;
import com.bus.management.service.BuslineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/bus")
@Validated
public class BusLineController {
    @Autowired
    private BuslineService buslineService;

    @PostMapping("/add")
    public Result<?> add(@RequestBody Busline bus) {
        if (bus == null) {
            return Result.error("参数错误");
        }
        if (bus.getBusid() == null) {
            return Result.error("请输入公交线路");
        }
        Busline busline = buslineService.getOne(Wrappers.<Busline>lambdaQuery().eq(Busline::getBusid, bus.getBusid()));
        if (busline != null) {
            return Result.error("该公交车已存在");
        }
        buslineService.save(bus);
        return Result.success();
    }

    @GetMapping("/getAll")
    public Result<?> getAll() {
        List<Busline> all = buslineService.list();
        return Result.success(all);
    }

    @GetMapping("/getByBusid")
    public Result<?> getByBusid(String busid) {
        Busline bus = buslineService.getOne(Wrappers.<Busline>lambdaQuery().eq(Busline::getBusid, busid));
        return Result.success(bus);
    }

    @PostMapping("/update")
    public Result<?> update(@RequestBody Busline bus) {
        buslineService.updateById(bus);
        return Result.success();
    }

    @DeleteMapping("/delete")
    public Result<?> delete(String id) {
        buslineService.removeById(id);
        return Result.success();
    }

}
