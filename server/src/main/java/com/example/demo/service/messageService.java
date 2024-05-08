package com.example.demo.service;

import com.example.demo.pojo.message;

import java.util.List;

public interface messageService {


    void add(message book);

    void deleteMessage(int id);

    List<message> getAllMessages();

}
