package com.bus.management.service.impl;

import com.bus.management.mapper.MessageMapper;
import com.bus.management.pojo.Message;
import com.bus.management.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Override
    public void add(Message book) {
        messageMapper.add(book.getUserid(), book.getUsername(), book.getContent(), book.getReply(), book.getAddtime());
    }

    @Override
    public void deleteMessage(int id) {
        messageMapper.deleteMessage(id);
    }

    @Override
    public List<Message> getAllMessages() {
        return messageMapper.getAllMessages();
    }

    @Override
    public List<Message> getReply(Integer username) {
        return messageMapper.getReply(username);
    }
}
