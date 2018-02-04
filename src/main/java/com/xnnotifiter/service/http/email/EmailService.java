package com.xnnotifiter.service.http.email;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.Date;

/**
 * Created by sven on 25/08/2017.
 */
@Service
public class EmailService {

    @Autowired
    private MailSenderInfo mailSenderInfo;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public boolean send(String[] mailTos, String subject, String content) {
        mailSenderInfo.setInfo(mailTos, subject, content);
        return send(mailSenderInfo);
    }

    public boolean send(String[] mailTos, String subject, String content, boolean isHTML) {
        mailSenderInfo.setInfo(mailTos, subject, content, isHTML);
        return send(mailSenderInfo);
    }

    public boolean send(String[] mailTos, String subject, String content, String[] attach) {
        mailSenderInfo.setInfo(mailTos, subject, content, false, attach);
        return send(mailSenderInfo);
    }

    public boolean send(String[] mailTos, String subject, String content, boolean isHTML, String[] attach) {
        mailSenderInfo.setInfo(mailTos, subject, content, isHTML, attach);
        return send(mailSenderInfo);
    }
    
    public boolean send(MailSenderInfo mailInfo) {

        EmailAuth authenticator = mailInfo.getAuth()? new EmailAuth(mailInfo.getAccount(), mailInfo.getPassword()): null;
        Session sendMailSession = Session.getDefaultInstance(mailInfo.getProperties(), authenticator);
        try {
            Message mailMessage = new MimeMessage(sendMailSession);
            mailMessage.setFrom(new InternetAddress(mailInfo.getAccount()));
            for (String mailTo: mailInfo.getMailTos()) {
                mailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(mailTo));
            }
            mailMessage.setSubject(mailInfo.getSubject());
            mailMessage.setSentDate(new Date());

            Multipart mainPart = null;
            BodyPart html = null;
            if (mailInfo.getHTML()) {
                mainPart = new MimeMultipart();
                html = new MimeBodyPart();
                html.setContent(mailInfo.getContent(), "text/html; charset=utf-8");
                mainPart.addBodyPart(html);
            } else {
                mailMessage.setText(mailInfo.getContent());
            }

            String[] attach = mailInfo.getAttach();
            if (attach != null && attach.length != 0) {
                mainPart = new MimeMultipart();
                for (String fileName: attach) {
                    File file = new File(fileName);
                    if (!file.exists()) continue;
                    html = new MimeBodyPart();
                    html.setFileName(file.getName());
                    html.setDataHandler(new DataHandler(new FileDataSource(fileName)));
                    mainPart.addBodyPart(html);
                }
            }

            if (mainPart != null) {
                mailMessage.setContent(mainPart);
                mailMessage.saveChanges();
            }

            Transport.send(mailMessage);
        } catch (MessagingException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * sohu-inc.com邮箱可用此方法发送
     * @param mail
     * @return
     */
    public boolean send(MailInfo mail) {

        try {
            HtmlEmail email = new HtmlEmail();
            email.setHostName(mail.getHost());
            email.setCharset(MailInfo.ENCODEING);// 字符编码集的设置
            email.addTo(mail.getReceivers());// 收件人的邮箱
            if (mail.getCc() != null) email.addCc(mail.getCc());// 添加抄送人
            email.setFrom(mail.getSender(), mail.getName());// 发送人的邮箱
            email.setAuthentication(mail.getUsername(), mail.getPassword());// 如果需要认证信息的话，设置认证：用户名-密码。分别为发件人在邮件服务器上的注册名称和密码
            email.setSubject(mail.getSubject());// 要发送的邮件主题
            email.setHtmlMsg(mail.getMessage());// 要发送的信息，由于使用了HtmlEmail，可以在邮件内容中使用HTML标签

            if (CollectionUtils.isNotEmpty(mail.getAttachments())) {//添加附件
                for (File attachment : mail.getAttachments()){
                    email.attach(attachment);
                }
            }

            String result = email.send(); // 发送
            System.out.println(result == null? "null": result);
            logger.debug(mail.getSender() + " sending to: " + mail.getReceivers() + " result: " + (result == null? "null": result));
        } catch (EmailException e) {
            e.printStackTrace();
            logger.info("error mail subject"+ mail.getSubject());
            System.out.println(e.toString());
            return false;
        }
        return true;
    }
}