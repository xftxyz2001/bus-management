package com.bus.management.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bus.management.domain.Busline;
import com.bus.management.mapper.BuslineMapper;
import com.bus.management.service.BuslineService;
import com.bus.management.vo.req.BusSearchReq;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description 针对表【busline】的数据库操作Service实现
 * @createDate 2024-05-09 11:07:10
 */
@Service
public class BuslineServiceImpl extends ServiceImpl<BuslineMapper, Busline>
        implements BuslineService {

    @Override
    public List<Busline> search(BusSearchReq req) {
        String routePattern = "%" + req.getStart() + "-%" + req.getEnd() + "%";
        return baseMapper.selectList(Wrappers.<Busline>lambdaQuery().like(Busline::getRoutesite, routePattern));
    }
}




