package com.bus.management.service;

import com.bus.management.pojo.Message;

import java.util.List;

public interface MessageService {


    void add(Message book);

    void deleteMessage(int id);

    List<Message> getAllMessages();

    List<Message> getReply(Integer username);
}
