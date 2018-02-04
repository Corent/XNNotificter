package com.xnnotifiter.schedule;

import com.xnnotifiter.model.Alert;
import com.xnnotifiter.service.dao.AlertDBService;
import com.xnnotifiter.service.http.AlertManagerService;
import com.xnnotifiter.service.http.PrometheusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
@EnableScheduling
public class Monitor {

    @Resource
    private AlertDBService alertDBService;

    @Resource
    private PrometheusService prometheusService;

    @Resource
    private AlertManagerService alertManagerService;

    @Resource(name = "taskExecutor")
    private TaskExecutor taskExecutor;
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Scheduled(cron = "${contab.monitor}")
    public void monitor() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        logger.info("Monitor: " + sdf.format(new Date()));

        List<String> hosts = prometheusService.activeAlertmanagers();
        logger.info(hosts.toString());
        if (CollectionUtils.isEmpty(hosts)) {
            logger.info("Monitor: no active alertmanagers");
            return;
        }

        Map<String, List<Alert>> alertMap = alertManagerService.alerts(hosts);
        alertMap.entrySet().forEach(e -> {
            List<Alert> alerts = e.getValue();
            logger.info("Host: " + e.getKey());
            logger.info("Alerts: " + alerts.toString());
            taskExecutor.execute(() -> alerts.forEach(alert -> alertDBService.saveOrUpdate(alert)));
        });
    }
}
