package com.bus.management.mapper;

import com.bus.management.pojo.Board;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BoardMapper {
    @Select("select * from board")
    List<Board> getAll();

    @Insert("insert into board  (title,content) values(#{title},#{content})")
    void add(String title, String content);

    @Delete("delete from board where id = #{id}")
    void deleteBoard(Integer id);

    @Update("update board set title=#{title},content=#{content} where id = #{id}")
    void revise(Integer id, String title, String content);

    @Select("SELECT * FROM board WHERE title LIKE CONCAT('%', #{keyword}, '%') OR content LIKE CONCAT('%', #{keyword}, '%') OR updateTime LIKE CONCAT('%', #{keyword}, '%') OR createTime LIKE CONCAT('%', #{keyword}, '%')")
    List<Board> searchNotices(String keyword);
}
