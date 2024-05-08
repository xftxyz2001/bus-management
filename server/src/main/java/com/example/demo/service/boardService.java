package com.example.demo.service;

import com.example.demo.pojo.board;

import java.util.List;

public interface boardService {
    List<board> getAll();

    void add(board message);

    void deleteBoard(Integer id);

    void revise(board message);

    List<board> searchNotices(String keyword);
}
