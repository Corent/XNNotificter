package com.xnnotifiter.service.dao;

import com.xnnotifiter.mapper.SMSMapper;
import com.xnnotifiter.model.dto.SMS;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SMSDBService {

    @Resource
    private SMSMapper smsMapper;

    public int save(SMS sms) {
        return smsMapper.insert(sms);
    }

    public int setSent(SMS sms) {
        return smsMapper.update(sms);
    }

    public List<SMS> listUnsents() {
        return smsMapper.findBySent(false);
    }

    public List<SMS> listAll() {
        return smsMapper.listAll();
    }
}
