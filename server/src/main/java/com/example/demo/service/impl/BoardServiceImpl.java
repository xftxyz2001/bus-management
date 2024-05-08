package com.example.demo.service.impl;

import com.example.demo.mapper.BoardMapper;
import com.example.demo.pojo.Board;
import com.example.demo.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {
    @Autowired
    private BoardMapper boardMapper;

    @Override
    public List<Board> getAll() {
        return boardMapper.getAll();
    }

    @Override
    public void add(Board message) {
        java.util.Date date1 = new java.util.Date();
        // DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//注意月和小时的格式为两个大写字母
        Date date = new Date(date1.getYear(), date1.getMonth(), date1.getDay());
        // String timenow = df.format(date);//将当前时间转换成特定格式的时间字符串，这样便可以插入到数据库中

        boardMapper.add(message.getTitle(), message.getContent());
    }

    @Override
    public void deleteBoard(Integer id) {
        boardMapper.deleteBoard(id);
    }

    @Override
    public void revise(Board message) {
        boardMapper.revise(message.getId(), message.getTitle(), message.getContent());
    }

    @Override
    public List<Board> searchNotices(String keyword) {
        return boardMapper.searchNotices(keyword);
    }
}
