package com.example.demo.controller;

import com.example.demo.pojo.Result;
import com.example.demo.pojo.User;
import com.example.demo.pojo.message;
import com.example.demo.service.UserService;
import com.example.demo.service.messageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/message")
@Validated
public class messageController {
    @Autowired
    private messageService messageService;

    @Autowired
    private UserService userServicel;
    //添加留言

    @PostMapping("/sayliuyan")
    public Result addcontext(message book) {
        User u = userServicel.findByUserName(book.getUsername());
        if (u == null) {
            return Result.error("该用户名不存在，请重新输入");
        } else {
            messageService.add(book);
            return Result.success();
        }

    }

    @DeleteMapping("/delete/{id}")
    public Result deleteMessage(@PathVariable int id) {
        messageService.deleteMessage(id);
        return Result.success("删除成功");

    }

    //显示所有留言
    @GetMapping("/getAll")
    public Result getAllMessages() {

        List<message> messages = messageService.getAllMessages();
        return Result.success(messages);

    }
}





