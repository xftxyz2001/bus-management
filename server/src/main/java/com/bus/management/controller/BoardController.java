package com.bus.management.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.bus.management.domain.Board;
import com.bus.management.result.Result;
import com.bus.management.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/board")
@Validated
public class BoardController {
    @Autowired
    private BoardService boardService;

    @GetMapping("/getAllboard")
    public Result<?> getAllBoard() {
        List<Board> list = boardService.list();
        return Result.success(list);
    }

    @PostMapping("/declare")
    public Result<?> declare(@RequestBody Board board) {
        boardService.save(board);
        return Result.success("success");
    }

    @DeleteMapping("/delete/{id}")
    public Result<?> delete(@PathVariable("id") Integer id) {
        boardService.removeById(id);
        return Result.success();
    }

    @PostMapping("/revise")
    public Result<?> revise(@RequestBody Board board) {
        boardService.updateById(board);
        return Result.success("success");
    }

    @GetMapping("/search")
    public Result<?> searchNotice(@RequestParam("keyword") String keyword) {
        List<Board> notices = boardService.list(Wrappers.<Board>lambdaQuery().like(Board::getTitle, keyword));
        return Result.success(notices);
    }

}


