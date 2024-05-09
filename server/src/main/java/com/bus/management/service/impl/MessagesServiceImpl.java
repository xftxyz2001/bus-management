package com.bus.management.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bus.management.domain.Messages;
import com.bus.management.service.MessagesService;
import com.bus.management.mapper.MessagesMapper;
import org.springframework.stereotype.Service;

/**
* @author 25810
* @description 针对表【messages】的数据库操作Service实现
* @createDate 2024-05-09 11:07:10
*/
@Service
public class MessagesServiceImpl extends ServiceImpl<MessagesMapper, Messages>
    implements MessagesService{

}




