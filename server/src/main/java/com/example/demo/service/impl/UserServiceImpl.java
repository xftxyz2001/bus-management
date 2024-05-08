package com.example.demo.service.impl;

import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.User;
import com.example.demo.service.EmailService;
import com.example.demo.service.UserService;
import com.example.demo.utils.Md5Util;
import com.example.demo.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private EmailService emailService;

    @Override
    public User findByID(Integer id) {
        return userMapper.findByID(id);
    }

    @Override
    public User findByUserName(String username) {
        return userMapper.findByUserName(username);
    }

    @Override
    public User findByEmail(String email) {
        return userMapper.findByEmail(email);
    }

    @Override
    public void register(String username, String password, int age, int gender, String phone) {
        //加密
        String md5String = Md5Util.getMD5String(password);
        System.out.println(md5String);
        //添加
        userMapper.add(username, md5String, age, gender, phone);
//        userMapper.add(username,password);
    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public void updatePwd(String newPwd) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        //自己改动过，将newPwd加密了
        userMapper.updatePwd(Md5Util.getMD5String(newPwd), id);


    }

    @Override
    public List<User> getAll() {
        return userMapper.getAll();

    }

    @Override
    public void deleteUser(Integer id) {
        userMapper.deleteUser(id);
    }

    @Override
    public void add(User a) {
        userMapper.zengjia(a.getUsername(), a.getAge(), a.getGender(), Md5Util.getMD5String(a.getPassword()), a.getPhone());
    }

    @Override
    public User getCurrentUser() {
        return null;
    }

    @Override
    public void sendResetPassword(String email) {
        User user = userMapper.findByEmail(email);
        if (user != null) {
            String newPassword = UUID.randomUUID().toString().substring(0, 8);
            user.setPassword(newPassword);  // 实际情况中，应使用强哈希加密密码
            userMapper.updatePwd(Md5Util.getMD5String(newPassword), user.getId());

            emailService.sendEmail(
                    user.getEmail(), "您的新密码", "您的新密码是： " + newPassword + "。 请尽快登录并且修改密码。"
            );
        }
    }

    @Override
    public void updateToken(String username, String token) {
        userMapper.updateToken(username, token);
    }


}
