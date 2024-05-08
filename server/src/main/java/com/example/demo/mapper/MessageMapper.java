package com.example.demo.mapper;

import com.example.demo.pojo.Message;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MessageMapper {

    @Insert("insert into messages (username,content,userid) values (#{username},#{content},#{userid})")
    void add(String username, String content, Integer userid);

    @Delete("delete from messages where id = #{id}")
    void deleteMessage(int id);

    @Select("select * from messages")
    List<Message> getAllMessages();
}
