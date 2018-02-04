package com.xnnotifiter.schedule;

import com.xnnotifiter.model.dto.SMS;
import com.xnnotifiter.service.dao.SMSDBService;
import com.xnnotifiter.service.http.SMService;
import com.xnnotifiter.service.http.email.EmailService;
import com.xnnotifiter.service.http.email.MailInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
@EnableScheduling
public class Notifiter {

    @Resource
    private SMService smService;
    @Resource
    private SMSDBService smsDBService;

    @Autowired
    private MailInfo mail;
    @Resource
    private EmailService emailService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Scheduled(cron = "${contab.notifiter}")
    public void notifiter() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        logger.info("Notifiter: " + sdf.format(new Date()));

        List<SMS> smsList = smsDBService.listUnsents();
        if (CollectionUtils.isEmpty(smsList)) {
            logger.info("Notifiter: no sms to send.");
        }
        mail.init(new String[] { "receiver@host" }, "Subject", "message");
        smsList.parallelStream().forEach(sms -> {
            smService.send(sms.getContent());
            sms.setSent(true);
            smsDBService.setSent(sms);
            emailService.send(mail);
        });
    }
}
