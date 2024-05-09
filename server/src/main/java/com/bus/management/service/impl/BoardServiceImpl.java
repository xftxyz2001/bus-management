package com.bus.management.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bus.management.domain.Board;
import com.bus.management.mapper.BoardMapper;
import com.bus.management.service.BoardService;
import org.springframework.stereotype.Service;

/**
 * @author 25810
 * @description 针对表【board】的数据库操作Service实现
 * @createDate 2024-05-09 11:07:10
 */
@Service
public class BoardServiceImpl extends ServiceImpl<BoardMapper, Board>
        implements BoardService {

}




