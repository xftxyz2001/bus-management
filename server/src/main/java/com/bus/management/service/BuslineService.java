package com.bus.management.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bus.management.domain.Busline;
import com.bus.management.vo.req.BusSearchReq;

import java.util.List;

/**
 * @description 针对表【busline】的数据库操作Service
 * @createDate 2024-05-09 11:07:10
 */
public interface BuslineService extends IService<Busline> {

    List<Busline> search(BusSearchReq req);
}
