package com.bus.management.mapper;

import com.bus.management.pojo.Message;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MessageMapper {

    @Insert("insert into messages (userid, username, content, reply, addtime) values (#{bookUserid}, #{username}, #{content}, #{reply}, #{addtime})")
    void add(Integer bookUserid, String username, String content, Integer reply, String addtime);

    @Delete("delete from messages where id = #{id}")
    void deleteMessage(int id);

    @Select("select * from messages where reply IS NULL")
    List<Message> getAllMessages();

    @Select("select * from messages where reply = #{id}")
    List<Message> getReply(Integer id);
}
