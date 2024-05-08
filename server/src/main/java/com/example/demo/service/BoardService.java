package com.example.demo.service;

import com.example.demo.pojo.Board;

import java.util.List;

public interface BoardService {
    List<Board> getAll();

    void add(Board message);

    void deleteBoard(Integer id);

    void revise(Board message);

    List<Board> searchNotices(String keyword);
}
