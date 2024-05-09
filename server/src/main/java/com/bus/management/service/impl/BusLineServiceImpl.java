package com.bus.management.service.impl;

import com.bus.management.mapper.BusLineMapper;
import com.bus.management.pojo.BusLine;
import com.bus.management.service.BusLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusLineServiceImpl implements BusLineService {
    @Autowired
    private BusLineMapper busLineMapper;

    @Override
    public void add(BusLine bus) {
        busLineMapper.tianjia(bus.getCity(), bus.getStartstation(), bus.getEndstation(), bus.getRoutesite(), bus.getBusid(), bus.getRuntime(), bus.getPrice());
    }

    @Override
    public BusLine findBybusid(String busid) {
        return busLineMapper.findBybusid(busid);
    }

    @Override
    public void registerbus(BusLine bus) {
        busLineMapper.zengjia(bus.getCity(), bus.getStartstation(), bus.getEndstation(), bus.getRoutesite(), bus.getBusid(), bus.getRuntime(), bus.getPrice());
    }

    @Override
    public List<BusLine> getAll() {
        return busLineMapper.getAll();
    }

    @Override
    public void update(BusLine bus) {
        busLineMapper.update(bus.getId(), bus.getCity(), bus.getStartstation(), bus.getEndstation(), bus.getRoutesite(), bus.getBusid(), bus.getRuntime(), bus.getPrice());
    }

    @Override
    public void delete(String id) {
        busLineMapper.delete(id);
    }
}