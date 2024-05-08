package com.example.demo.service;

import com.example.demo.pojo.Message;

import java.util.List;

public interface MessageService {


    void add(Message book);

    void deleteMessage(int id);

    List<Message> getAllMessages();

}
