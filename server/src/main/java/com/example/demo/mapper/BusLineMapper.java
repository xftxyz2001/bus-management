package com.example.demo.mapper;

import com.example.demo.pojo.BusLine;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BusLineMapper {
    @Insert("insert into busline(city,startstation,endstation,routesite,busid,runtime,price) values (#{city},#{startstation},#{endstation},#{routesite},#{busid},#{runtime},#{price})")
    void tianjia(String city, String startstation, String endstation, String routesite, String busid, String runtime, String price);

    @Select("select * from busline where busid = #{busid}")
    BusLine findBybusid(String busid);

    @Insert("insert into busline(city,startstation,endstation,routesite,busid,runtime,price) values (#{city},#{startstation},#{endstation},#{routesite},#{busid},#{runtime},#{price})")
    void zengjia(String city, String startstation, String endstation, String routesite, String busid, String runtime, String price);

    @Select("select * from busline")
    List<BusLine> getAll();

    @Update("update busline set city = #{city},startstation = #{startstation},endstation = #{endstation},routesite = #{routesite},busid = #{busid},runtime = #{runtime},price = #{price} where id = #{id}")
    void update(Integer id, String city, String startstation, String endstation, String routesite, String busid, String runtime, String price);

    @Delete("delete from busline where id = #{id}")
    void delete(String id);
}
