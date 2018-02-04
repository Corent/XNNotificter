package com.xnnotifiter.service.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Service
public class SMService {

    @Resource(name = "taskExecutor")
    private TaskExecutor taskExecutor;

    @Value("${alert.receivers}")
    private String receivers;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void send(String content) {
        if (receivers == null || receivers.length() == 0) return;
        send(Arrays.asList(receivers.split(",")), content);
    }

    public void send(List<String> mobiles, String content) {
        taskExecutor.execute(() -> {
            //TODO: send alert sms
            logger.info("SMService: send sms to " + mobiles + ", content=【Alert】" + content);
        });
    }
}
