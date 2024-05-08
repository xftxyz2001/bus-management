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
@CrossOrigin
public class busLineController {
    @Autowired
    private busLineService busLineService;

    @PostMapping("/add")
    public Result add(busLine bus) {
        System.out.println(bus.getBusid());
        busLine b = busLineService.findBybusid(bus.getBusid());
        System.out.println(bus.getCity());
        System.out.println(bus.getRuntime());
        System.out.println(bus.getPrice());
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


}
