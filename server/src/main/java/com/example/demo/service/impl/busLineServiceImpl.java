package com.example.demo.service.impl;

import com.example.demo.mapper.busLineMapper;
import com.example.demo.pojo.busLine;
import com.example.demo.service.busLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class busLineServiceImpl implements busLineService {
    @Autowired
    private busLineMapper busLineMapper;
    @Override
    public void add(busLine bus) {
        busLineMapper.tianjia(bus.getCity(),bus.getStartstation(),bus.getEndstation(),bus.getRoutesite(),bus.getBusid(),bus.getRuntime(),bus.getPrice());
    }

    @Override
    public busLine findBybusid(String busid) {
        return busLineMapper.findBybusid(busid);
    }

    @Override
    public void registerbus(busLine bus) {
        busLineMapper.zengjia(bus.getCity(),bus.getStartstation(),bus.getEndstation(),bus.getRoutesite(),bus.getBusid(),bus.getRuntime(),bus.getPrice());
    }

    @Override
    public List<busLine> getAll() {
        return busLineMapper.getAll();
    }
}
