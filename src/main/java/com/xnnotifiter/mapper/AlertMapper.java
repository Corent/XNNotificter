package com.xnnotifiter.mapper;

import com.xnnotifiter.model.dto.AlertDTO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value = "alertMapper")
public interface AlertMapper {

    @Insert("INSERT INTO alert(fingerprint, alertname, state, json) VALUES (#{fingerprint}, #{alertname}, #{state}, #{json})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    int insert(AlertDTO alert);

    @Delete("DELETE FROM alert WHERE id=#{id}")
    int deletById(@Param("id") int idd);

    @Delete("DELETE FROM alert WHERE fingerprint=#{fingerprint}")
    int deletByFP(@Param("fingerprint") String fingerprint);

    @Update("UPDATE alert SET alertname=#{alertname}, state=#{state}, json=#{json} WHERE fingerprint=#{fingerprint}")
    int update(AlertDTO alert);

    @Select("SELECT * FROM alert WHERE fingerprint = #{fingerprint}")
    @Results(id = "alertMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "fingerprint", property = "fingerprint"),
            @Result(column = "alertname", property = "alertname"),
            @Result(column = "state", property = "state"),
            @Result(column = "json", property = "json")
    })
    AlertDTO findById(@Param("id") int id);

    @Select("SELECT * FROM alert WHERE fingerprint = #{fingerprint}")
    @ResultMap("alertMap")
    AlertDTO findByFP(@Param("fingerprint") String fingerprint);

    @Select("SELECT * FROM alert WHERE state = #{state}")
    @ResultMap("alertMap")
    List<AlertDTO> findByState(@Param("state") String state);

    @Select("SELECT * FROM alert")
    @ResultMap("alertMap")
    List<AlertDTO> listAll();
}
