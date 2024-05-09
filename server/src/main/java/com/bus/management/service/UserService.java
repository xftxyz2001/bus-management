package com.bus.management.service;

import com.bus.management.pojo.User;

import java.util.List;

public interface UserService {
    User findByID(Integer id);

    //根据用户名查询用户
    User findByUserName(String username);

    User findByEmail(String email);

    //注册
    void register(String username, String password, int age, int gender, String phone);

    //更新
    void update(User user);

    void updatePwd(String newPwd);

    //查询所有用户信息
    List<User> getAll();

    // 刪除用戶
    void deleteUser(Integer id);

    //添加信息
    void add(User a);

    User getCurrentUser();


    void sendResetPassword(String email);

    void updateToken(String username, String token);
}
