package com.bus.management.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bus.management.domain.User;
import com.bus.management.mapper.UserMapper;
import com.bus.management.service.EmailService;
import com.bus.management.service.UserService;
import com.bus.management.utils.Md5Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @description 针对表【user(用户表)】的数据库操作Service实现
 * @createDate 2024-05-09 11:07:10
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    private final EmailService emailService;

    @Override
    public void frogetPassword(String email) {
        User user = baseMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getEmail, email));
        if (user != null) {
            String newPassword = UUID.randomUUID().toString().substring(0, 8);
            user.setPassword(Md5Util.getMD5String(newPassword));  // 实际情况中，应使用强哈希加密密码
            baseMapper.updateById(user);

            emailService.sendEmail(
                    user.getEmail(), "您的新密码", "您的新密码是： " + newPassword + "。 请尽快登录并且修改密码。"
            );
        }
    }
}




