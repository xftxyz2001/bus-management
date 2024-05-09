package com.bus.management.service;

import com.bus.management.pojo.BusLine;

import java.util.List;

public interface BusLineService {
    void add(BusLine bus);

    BusLine findBybusid(String busid);

    void registerbus(BusLine bus);

    List<BusLine> getAll();

    void update(BusLine bus);

    void delete(String id);
}
