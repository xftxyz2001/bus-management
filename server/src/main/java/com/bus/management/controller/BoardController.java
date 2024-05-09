package com.bus.management.controller;


import com.bus.management.pojo.Board;
import com.bus.management.pojo.Result;
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
    public Result getAllBoard() {
        List<Board> all = boardService.getAll();
        return Result.success(all);
    }

    @PostMapping("/declare")
    public Result declare(@RequestBody Board message) {
        boardService.add(message);
//        System.out.println(message.getTitle());
        return Result.success("success");
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Integer id) {
        boardService.deleteBoard(id);
        return Result.success();
    }

    @PostMapping("/revise")
    public Result revise(@RequestBody Board message) {
        boardService.revise(message);
        return Result.success("success");
    }

    @GetMapping("/search")
    public Result<List<Board>> searchNotice(@RequestParam String keyword) {
        List<Board> notices = boardService.searchNotices(keyword);
        return Result.success(notices);
    }

}


