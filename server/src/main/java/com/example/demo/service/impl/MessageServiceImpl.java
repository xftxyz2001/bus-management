package com.example.demo.service.impl;

import com.example.demo.mapper.MessageMapper;
import com.example.demo.pojo.Message;
import com.example.demo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Override
    public void add(Message book) {
        messageMapper.add(book.getUsername(), book.getContent(), book.getUserid());
    }

    @Override
    public void deleteMessage(int id) {
        messageMapper.deleteMessage(id);
    }

    @Override
    public List<Message> getAllMessages() {
        return messageMapper.getAllMessages();
    }
}
