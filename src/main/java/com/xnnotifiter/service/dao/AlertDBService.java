package com.xnnotifiter.service.dao;

import com.xnnotifiter.convertor.AlertConvertor;
import com.xnnotifiter.mapper.AlertMapper;
import com.xnnotifiter.model.Alert;
import com.xnnotifiter.model.dto.AlertDTO;
import com.xnnotifiter.model.dto.SMS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlertDBService {

    @Resource
    private AlertMapper alertMapper;

    @Resource
    private AlertConvertor alertConvertor;

    @Resource
    private SMSDBService smsDBService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public Integer saveOrUpdate(Alert alert) {
        AlertDTO alertDTO = alertConvertor.convert(alert);
        AlertDTO tmp = alertMapper.findByFP(alertDTO.getFingerprint());
        if (tmp == null) {
            logger.info("AlertDBService: save alert with fingerprint=" + alertDTO.getFingerprint());
            smsDBService.save(new SMS(alert.getFingerprint(), alert.getAnnotations().toString()));
            return alertMapper.insert(alertDTO);
        } else {
            if (!tmp.getState().equals(alertDTO.getState())) {
                logger.info("AlertDBService: update alert with fingerprint=" + alertDTO.getFingerprint());
                return alertMapper.update(alertDTO);
            }
        }
        return null;
    }

    public List<Alert> listAll() {
        List<AlertDTO> alertDTOS = alertMapper.listAll();
        if (CollectionUtils.isEmpty(alertDTOS)) return new ArrayList<Alert>();
        return alertDTOS.stream().map(alertDTO -> alertConvertor.convert(alertDTO)).collect(Collectors.toList());
    }
}
