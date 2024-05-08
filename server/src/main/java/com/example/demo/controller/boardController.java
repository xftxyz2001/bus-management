package com.example.demo.controller;


import com.example.demo.pojo.Result;
import com.example.demo.pojo.board;
import com.example.demo.service.boardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
@CrossOrigin
@Validated
public class boardController {
    @Autowired
    private boardService boardService;

    @GetMapping("/getAllboard")
    public Result getAllBoard() {
        List<board> all = boardService.getAll();
        return Result.success(all);
    }

    @PostMapping("/declare")
    public Result declare(@RequestBody board message) {
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
    public Result revise(@RequestBody board message) {
        boardService.revise(message);
        return Result.success("success");
    }

    @GetMapping("/search")
    public Result<List<board>> searchNotice(@RequestParam String keyword) {
        List<board> notices = boardService.searchNotices(keyword);
        return Result.success(notices);
    }

}


