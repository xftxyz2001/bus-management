package com.bus.management.controller;

import com.bus.management.config.Env;
import com.bus.management.pojo.Result;
import com.bus.management.pojo.User;
import com.bus.management.service.EmailService;
import com.bus.management.service.UserService;
import com.bus.management.utils.JwtUtil;
import com.bus.management.utils.Md5Util;
import com.bus.management.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
@Validated
@SuppressWarnings("all")
public class UserController {


    @Autowired
    private UserService userService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private EmailService emailService;

    @PostMapping("/add")
    public Result add(@RequestBody User a) {
        User u = userService.findByUserName(a.getUsername());
        if (u == null) {
            userService.add(a);
            return Result.success();
        } else {
            return Result.error("该用户名已被占用");
        }
    }


    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{3,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password, @RequestParam int age, @RequestParam int gender, @RequestParam String phone) {

        User u = userService.findByUserName(username);

        if (u == null) {
            //没有占用
            //注册
            userService.register(username, password, age, gender, phone);
            return Result.success();

        } else {
            //占用
            return Result.error("用户名已经被占用");
        }


    }

    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp = "^\\S{3,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password) {
        //根据用户名查询用户
        User loginUser = userService.findByUserName(username);
        //判断该用户是否存在
        if (loginUser == null) {
            return Result.error("用户名错误");
        }

        //判断密码是否正确  loginUser对象中的password是密文
        if (Md5Util.getMD5String(password).equals(loginUser.getPassword())) {
            //登录成功
            // Map<String, Object> claims = new HashMap<>();
            // claims.put("id", loginUser.getId());
            // claims.put("username", loginUser.getUsername());
            String token = JwtUtil.generateToken(loginUser.getId());
            //把token存储到redis中
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            operations.set(token, token, JwtUtil.EXPIRE_TIME, TimeUnit.MILLISECONDS);
            userService.updateToken(username, token);
            return Result.success(token);
            //return Result.success("密码正确");
        }
        return Result.error("密码错误");
    }

    @GetMapping("/userInfo")
    public Result<User> userInfo(@RequestHeader(name = "Authorization", required = false) String token) {
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        //  Map<String,Object>map = JwtUtil.parseToken(token);
//        String username = (String)map.get("username");
        User user = userService.findByUserName(username);
        return Result.success(user);
    }


    //获取所有用户信息
    @GetMapping("/getAll")
    public Result getAll() {
        List<User> all = userService.getAll();
        return Result.success(all);
    }

    //获取当前登录用户信息
    @GetMapping("/current")
    public Result<User> getCurrent(@RequestAttribute(Env.CURRENT_REQUEST_USER) Integer userId) {
        User user = userService.findByID(userId);
        user.setPassword(null);
        return Result.success(user);
    }


    @PutMapping("/update")
    public Result update(@RequestBody User user) {
        userService.update(user);
        return Result.success();
    }


    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Integer id) {
        userService.deleteUser(id);
        return Result.success();
    }


    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String, String> params, @RequestHeader("Authorization") String token) {
        //1、检验参数
        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");

        if (!StringUtils.hasLength(oldPwd) || !StringUtils.hasLength(newPwd) || !StringUtils.hasLength(rePwd)) {
            return Result.error("缺少必要的参数");
        }

        //原密码是否正确
        //调用userService根据用户名拿到原密码,再和old_pwd比对
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User loginUser = userService.findByUserName(username);
        if (!loginUser.getPassword().equals(Md5Util.getMD5String(oldPwd))) {
            return Result.error("原密码填写不正确");
        }

        //newPwd和rePwd是否一样
        if (!rePwd.equals(newPwd)) {
            return Result.error("两次填写的新密码不一样");
        }

        //2.调用service完成密码更新
        userService.updatePwd(newPwd);
        //删除redis中对应的token
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.getOperations().delete(token);
//        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
//        operations.getOperations().delete(token);
        return Result.success();

    }

    @PostMapping("/forget")
    public Result frogetPassword(@RequestParam String email) {
        System.out.println(email);
        userService.sendResetPassword(email);
        return Result.success("重置密码邮件已发送至您的邮箱。");
    }

    // 获取用户角色
    @GetMapping("/role")
    public Result<String> getRole(@RequestAttribute(Env.CURRENT_REQUEST_USER) Integer userId) {
        User user = userService.findByID(userId);
        if (User.ROLE_USER.equals(user.getIdentify())) {
            return Result.success("用户");
        } else if (User.ROLE_ADMIN.equals(user.getIdentify())) {
            return Result.success("管理员");
        } else {
            return Result.success("未知");
        }
    }
}
