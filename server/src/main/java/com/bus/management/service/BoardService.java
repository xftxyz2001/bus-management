package com.bus.management.service;

import com.bus.management.pojo.Board;

import java.util.List;

public interface BoardService {
    List<Board> getAll();

    void add(Board message);

    void deleteBoard(Integer id);

    void revise(Board message);

    List<Board> searchNotices(String keyword);
}
