package com.bus.management.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.bus.management.config.Env;
import com.bus.management.domain.User;
import com.bus.management.result.Result;
import com.bus.management.service.UserService;
import com.bus.management.utils.JwtUtil;
import com.bus.management.utils.Md5Util;
import com.bus.management.vo.req.ForgetPasswordReq;
import com.bus.management.vo.req.LoginReq;
import com.bus.management.vo.req.RegisterReq;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;

    private final StringRedisTemplate stringRedisTemplate;

    @PostMapping("/add")
    public Result<?> add(@RequestBody User user) {
        User u = userService.getOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, user.getUsername()));
        if (u == null) {
            userService.save(user);
            return Result.success();
        } else {
            return Result.error("该用户名已被占用");
        }
    }


    @PostMapping("/register")
    public Result<?> register(@RequestBody RegisterReq req) {

        User u = userService.getOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, req.getUsername()));

        if (u != null) {
            return Result.error("用户名已经被占用");
        }
        u = new User();
        u.setUsername(req.getUsername());
        u.setPassword(Md5Util.getMD5String(req.getPassword()));
        u.setAge(req.getAge());
        u.setGender(req.getGender());
        u.setPhone(req.getPhone());
        u.setIdentify(User.ROLE_USER);
        userService.save(u);
        return Result.success();
    }

    @PostMapping("/login")
    public Result<?> login(@RequestBody LoginReq req) {
        //根据用户名查询用户
        User loginUser = userService.getOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, req.getUsername()));
        //判断该用户是否存在
        if (loginUser == null) {
            return Result.error("用户名错误");
        }

        //判断密码是否正确  loginUser对象中的password是密文
        if (!Md5Util.getMD5String(req.getPassword()).equals(loginUser.getPassword())) {
            return Result.error("密码错误");
        }
        //登录成功
        String token = JwtUtil.generateToken(loginUser.getId());
        //把token存储到redis中
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.set(token, token, JwtUtil.EXPIRE_TIME, TimeUnit.MILLISECONDS);
        // userService.updateToken(username, token);
        userService.update(Wrappers.<User>lambdaUpdate().eq(User::getUsername, req.getUsername()).set(User::getToken, token));
        return Result.success(token);
    }

    @GetMapping("/userInfo")
    public Result<?> userInfo(@RequestAttribute(Env.CURRENT_REQUEST_USER) Integer userId) {
        User user = userService.getById(userId);
        return Result.success(user);
    }


    //获取所有用户信息
    @GetMapping("/getAll")
    public Result<?> getAll() {
        List<User> all = userService.list();
        return Result.success(all);
    }

    //获取当前登录用户信息
    @GetMapping("/current")
    public Result<?> getCurrent(@RequestAttribute(Env.CURRENT_REQUEST_USER) Integer userId) {
        User user = userService.getById(userId);
        user.setPassword(null);
        return Result.success(user);
    }


    @PutMapping("/update")
    public Result<?> update(@RequestBody User user) {
        userService.updateById(user);
        return Result.success();
    }


    @DeleteMapping("/delete/{id}")
    public Result<?> delete(@PathVariable("id") Integer id) {
        userService.removeById(id);
        return Result.success();
    }


    @PatchMapping("/updatePwd")
    public Result<?> updatePwd(@RequestBody Map<String, String> params, @RequestAttribute(Env.CURRENT_REQUEST_USER) Integer userId) {
        //1、检验参数
        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");

        if (!StringUtils.hasLength(oldPwd) || !StringUtils.hasLength(newPwd) || !StringUtils.hasLength(rePwd)) {
            return Result.error("缺少必要的参数");
        }

        //原密码是否正确
        User loginUser = userService.getById(userId);
        if (!loginUser.getPassword().equals(Md5Util.getMD5String(oldPwd))) {
            return Result.error("原密码填写不正确");
        }

        //newPwd和rePwd是否一样
        if (!rePwd.equals(newPwd)) {
            return Result.error("两次填写的新密码不一样");
        }

        //2.调用service完成密码更新
        loginUser.setPassword(Md5Util.getMD5String(newPwd));
        userService.updateById(loginUser);
        //删除redis中对应的token
        stringRedisTemplate.opsForValue().getOperations().delete(loginUser.getToken());
        return Result.success();

    }

    @PostMapping("/forget")
    public Result<?> forgetPassword(@RequestBody ForgetPasswordReq req) {
        userService.forgetPassword(req.getEmail());
        return Result.success("重置密码邮件已发送至您的邮箱。");
    }

    // 获取用户角色
    @GetMapping("/role")
    public Result<?> getRole(@RequestAttribute(Env.CURRENT_REQUEST_USER) Integer userId) {
        User user = userService.getById(userId);
        if (User.ROLE_USER.equals(user.getIdentify())) {
            return Result.success("用户");
        } else if (User.ROLE_ADMIN.equals(user.getIdentify())) {
            return Result.success("管理员");
        } else {
            return Result.success("未知");
        }
    }
}
