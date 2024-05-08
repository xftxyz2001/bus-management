package com.example.demo.service;

import com.example.demo.pojo.busLine;

import java.util.List;

public interface busLineService {
    void add(busLine bus);

    busLine findBybusid(String busid);

    void registerbus(busLine bus);

    List<busLine> getAll();
}
