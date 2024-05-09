package com.bus.management.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bus.management.domain.User;

/**
 * @description 针对表【user(用户表)】的数据库操作Service
 * @createDate 2024-05-09 11:07:10
 */
public interface UserService extends IService<User> {

    void frogetPassword(String email);
}
