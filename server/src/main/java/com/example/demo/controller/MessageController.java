package com.example.demo.controller;

import com.example.demo.pojo.Message;
import com.example.demo.pojo.Result;
import com.example.demo.pojo.User;
import com.example.demo.service.MessageService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/message")
@Validated
public class MessageController {
    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userServicel;

    // 添加留言
    @PostMapping("/sayliuyan")
    public Result<?> addcontext(Message book) {
        User u = userServicel.findByUserName(book.getUsername());
        if (u == null) {
            return Result.error("该用户名不存在，请重新输入");
        } else {
            messageService.add(book);
            return Result.success();
        }
    }

    @DeleteMapping("/delete/{id}")
    public Result<String> deleteMessage(@PathVariable int id) {
        messageService.deleteMessage(id);
        return Result.success("删除成功");
    }

    // 显示所有留言
    @GetMapping("/getAll")
    public Result<List<Message>> getAllMessages() {
        List<Message> messages = messageService.getAllMessages();
        return Result.success(messages);
    }

    @GetMapping("/getRe[ly")
    public Result<List<Message>> getReply(@RequestParam Integer id) {
        List<Message> messages = messageService.getReply(id);
        return Result.success(messages);
    }

}

