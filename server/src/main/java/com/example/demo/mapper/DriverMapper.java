package com.example.demo.mapper;

import com.example.demo.pojo.Driver;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DriverMapper {
    @Select("select * from  Driver")
    List<Driver> getAllDrivers();

    @Insert("insert into Driver(Name, Age, Gender, LicenseNumber, Phone, WorkLocation) values (#{name},#{age},#{gender},#{licenseNumber},#{phone},#{workLocation})")
    void addDriver(String name, int age, String gender, String licenseNumber, String phone, String workLocation);


    @Select("select * from driver where DriverID = #{id}")
    Driver getDriverById(int id);

    @Update("update driver set Name = #{name}, Age = #{age}, Gender = #{gender}, LicenseNumber = #{licenseNumber}, Phone = #{phone}, WorkLocation = #{workLocation} where DriverID = #{driverID}")
    void updateDriver(Driver driver);

    @Delete("delete from driver where DriverID = #{id}")
    void deleteDriver(int id);

    @Select("select * from driver where Name = #{driverName}")
    List<Driver> getByDriverName(String driverName);
}
