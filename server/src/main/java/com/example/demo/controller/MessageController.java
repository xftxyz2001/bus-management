package com.example.demo.controller;

import com.example.demo.config.Env;
import com.example.demo.pojo.Message;
import com.example.demo.pojo.Result;
import com.example.demo.pojo.User;
import com.example.demo.service.MessageService;
import com.example.demo.service.UserService;
import com.example.demo.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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
    public Result<?> addcontext(@RequestBody Message book, @RequestAttribute(Env.CURRENT_REQUEST_USER) Integer userId) {
        User u = userServicel.findByID(userId);
        if (u == null) {
            return Result.error("用户未登录");
        }
        if (!StringUtils.hasLength(book.getContent())) {
            return Result.error("留言内容不能为空");
        }
        book.setUserid(userId);
        book.setUsername(u.getUsername());
        book.setAddtime(DateUtils.nowString());
        messageService.add(book);
        return Result.success(book.getReply() != null ? "回复成功" : "留言成功");
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
        messages.sort((o1, o2) -> o2.getId() - o1.getId());
        return Result.success(messages);
    }

    @GetMapping("/getReply")
    public Result<List<Message>> getReply(@RequestParam Integer id) {
        List<Message> messages = messageService.getReply(id);
        messages.sort((o1, o2) -> o2.getId() - o1.getId());
        return Result.success(messages);
    }

}

