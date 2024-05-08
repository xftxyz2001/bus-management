package com.example.demo.mapper;

import com.example.demo.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper  {
    @Select("select *from user where id = #{id}")
    public User findByID(Integer id);

    @Select("select * from user where username = #{username}")
    User findByUserName(String username);

    @Insert("insert into user(username,password,age,gender,phone) values (#{username},#{password},#{age},#{gender},#{phone})")
    void add(String username, String password,int age,int gender,String phone);

    @Update("update user set age = #{age},gender=#{gender},phone=#{phone} where id = #{id}")
    void update(User user);

    @Update("update user set password = #{newPwd} where id = #{id} ")
    void updatePwd(String newPwd,Integer id);

    @Select("select  * from user")
    List<User> getAll();

    @Delete("delete from user where id = #{id}")
    void deleteUser(Integer id);
    @Insert("insert into user(username,age,gender,password,phone) values (#{username},#{age},#{gender},#{password},#{phone})")
    void zengjia(String username, short age, short gender, String password, String phone);

    @Select("select * from user where email= #{email}")
    User findByEmail(String email);

    @Update("update user set token = #{token} where username = #{username}")
    void updateToken(String username, String token);
}
