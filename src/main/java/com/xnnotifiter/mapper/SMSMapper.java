package com.xnnotifiter.mapper;

import com.xnnotifiter.model.dto.SMS;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value = "smsMapper")
public interface SMSMapper {

    @Insert("INSERT INTO sms(fingerprint, content, sent) VALUES (#{fingerprint}, #{content}, #{sent})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    int insert(SMS sms);

    @Update("UPDATE sms SET sent=#{sent} WHERE fingerprint=#{fingerprint}")
    int update(SMS sms);

    @Select("SELECT * FROM sms WHERE sent = #{sent}")
    @Results(id = "smsMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "fingerprint", property = "fingerprint"),
            @Result(column = "content", property = "content"),
            @Result(column = "sent", property = "sent"),
            @Result(column = "ctime", property = "ctime"),
            @Result(column = "utime", property = "utime")
    })
    List<SMS> findBySent(@Param("sent") boolean sent);

    @Select("SELECT * FROM sms")
    List<SMS> listAll();
}
