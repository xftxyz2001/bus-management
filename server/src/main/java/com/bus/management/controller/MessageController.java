package com.bus.management.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.bus.management.config.Env;
import com.bus.management.domain.Messages;
import com.bus.management.domain.User;
import com.bus.management.result.Result;
import com.bus.management.service.MessagesService;
import com.bus.management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/message")
@Validated
public class MessageController {
    @Autowired
    private MessagesService messageService;

    @Autowired
    private UserService userService;

    // 添加留言
    @PostMapping("/sayliuyan")
    public Result<?> addcontext(@RequestBody Messages msg, @RequestAttribute(Env.CURRENT_REQUEST_USER) Integer userId) {
        User u = userService.getById(userId);
        if (u == null) {
            return Result.error("用户未登录");
        }
        if (!StringUtils.hasLength(msg.getContent())) {
            return Result.error("留言内容不能为空");
        }
        msg.setUserid(u.getId().longValue());
        msg.setUsername(u.getUsername());
        msg.setAddtime(new Date());
        messageService.save(msg);
        return Result.success(msg.getReply() != null ? "回复成功" : "留言成功");
    }

    @DeleteMapping("/delete/{id}")
    public Result<String> deleteMessage(@PathVariable("id") Integer id) {
        messageService.removeById(id);
        return Result.success("删除成功");
    }

    // 显示所有留言
    @GetMapping("/getAll")
    public Result<?> getAllMessages() {
        List<Messages> messages = messageService.list(Wrappers.<Messages>lambdaQuery().isNull(Messages::getReply));
        messages.sort((o1, o2) -> Math.toIntExact(o2.getId() - o1.getId()));
        return Result.success(messages);
    }

    @GetMapping("/getReply")
    public Result<?> getReply(@RequestParam("id") Integer id) {
        List<Messages> messages = messageService.list(Wrappers.<Messages>lambdaQuery().eq(Messages::getReply, id));
        messages.sort((o1, o2) -> Math.toIntExact(o2.getId() - o1.getId()));
        return Result.success(messages);
    }

}

