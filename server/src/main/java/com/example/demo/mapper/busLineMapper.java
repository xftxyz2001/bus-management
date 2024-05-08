package com.example.demo.mapper;

import com.example.demo.pojo.busLine;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface busLineMapper {
    @Insert("insert into busline(city,startstation,endstation,routesite,busid,runtime,price) values (#{city},#{startstation},#{endstation},#{routesite},#{busid},#{runtime},#{price})")
    void tianjia(String city,String startstation,String endstation,String routesite,String busid,String runtime,String price);

    @Select("select * from busline where busid = #{busid}")
    busLine findBybusid(String busid);
    @Insert("insert into busline(city,startstation,endstation,routesite,busid,runtime,price) values (#{city},#{startstation},#{endstation},#{routesite},#{busid},#{runtime},#{price})")
    void zengjia(String city,String startstation,String endstation,String routesite,String busid,String runtime,String price);

    @Select("select * from busline")
    List<busLine> getAll();
}
