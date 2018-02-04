package com.xnnotifiter.service.http.email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

/**
 * Created by sven on 22/09/2017.
 */
@Component
public class MailInfo {

    public static final String ENCODEING = "utf-8";

    @Value("${email_host}")
    private String host;

    @Value("${email_account}")
    private String username;

    @Value("${email_password}")
    private String password;

    @Value("${email_account}")
    private String name;

    @Value("${email_account}")
    private String sender;

    private String[] receivers;

    private String[] cc;

    private String subject;

    private String message;

    private List<File> attachments;

    public MailInfo() {}

    public MailInfo init(String[] receivers, String subject, String message) {
        this.receivers = receivers;
        this.subject = subject;
        this.message = message;
        return this;
    }

    public static String getENCODEING() {
        return ENCODEING;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String[] getReceivers() {
        return receivers;
    }

    public void setReceivers(String[] receivers) {
        this.receivers = receivers;
    }

    public String[] getCc() {
        return cc;
    }

    public void setCc(String[] cc) {
        this.cc = cc;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<File> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<File> attachments) {
        this.attachments = attachments;
    }
}
