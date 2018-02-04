package com.xnnotifiter.convertor;

import com.alibaba.fastjson.JSON;
import com.xnnotifiter.model.Alert;
import com.xnnotifiter.model.dto.AlertDTO;
import org.springframework.stereotype.Component;

@Component
public class AlertConvertor {

    public AlertDTO convert(Alert alert) {
        return new AlertDTO() {{
            setFingerprint(alert.getFingerprint());
            setAlertname(alert.getLabels().getAlertname());
            setState(alert.getStatus().getState());
            setJson(alert.toString());
        }};
    }

    public Alert convert(AlertDTO alertDTO) {
        return JSON.parseObject(alertDTO.getJson(), Alert.class);
    }
}
