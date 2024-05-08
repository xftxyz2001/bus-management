package com.example.demo.service.impl;

import com.example.demo.mapper.messageMapper;
import com.example.demo.pojo.message;
import com.example.demo.service.messageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class messageServiceImpl implements messageService {

    @Autowired
    private messageMapper messageMapper;

    @Override
    public void add(message book) {
        messageMapper.add(book.getUsername(), book.getContent(), book.getUserid());
    }

    @Override
    public void deleteMessage(int id) {
        messageMapper.deleteMessage(id);
    }

    @Override
    public List<message> getAllMessages() {
        return messageMapper.getAllMessages();
    }
}
