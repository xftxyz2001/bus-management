package com.bus.management.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.bus.management.domain.Busline;
import com.bus.management.result.Result;
import com.bus.management.service.BuslineService;
import com.bus.management.vo.req.BusSearchReq;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;


@RestController
@RequestMapping("/bus")
@RequiredArgsConstructor
public class BusLineController {
    private final BuslineService buslineService;

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
    public Result<?> getByBusid(@RequestParam("busid") Integer busid) {
        Busline bus = buslineService.getOne(Wrappers.<Busline>lambdaQuery().eq(Busline::getBusid, busid));
        return Result.success(bus);
    }

    @PostMapping("/update")
    public Result<?> update(@RequestBody Busline bus) {
        buslineService.updateById(bus);
        return Result.success();
    }

    @DeleteMapping("/delete")
    public Result<?> delete(@RequestParam("id") Integer id) {
        buslineService.removeById(id);
        return Result.success();
    }

    @PostMapping("/search")
    public Result<?> search(@RequestBody BusSearchReq req) {
        List<Busline> list = buslineService.search(req);
        return Result.success(list);
    }

    @GetMapping("/getStation")
    public Result<?> getStation(@RequestParam("keyword") String keyword) {
        List<Busline> list = buslineService.list(Wrappers.<Busline>lambdaQuery().like(Busline::getRoutesite, keyword));
        List<String> res = list.stream().map(Busline::getRoutesite).flatMap(s -> {
            String[] split = s.split("-");
            return Stream.of(split);
        }).distinct().filter(s -> s.contains(keyword)).toList();
        return Result.success(res);
    }


}
