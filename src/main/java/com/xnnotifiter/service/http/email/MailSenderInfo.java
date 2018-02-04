package com.xnnotifiter.service.http.email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class MailSenderInfo {

    private String[] mailTos;
    private String subject;
    private String content;
    private Boolean isHTML;
    private String[] attach;

    @Value("${email_host}")
    private String host;

    @Value("${email_port}")
    private String port;

    @Value("${email_auth}")
    private Boolean auth;

    @Value("${email_account}")
    private String account;

    @Value("${email_password}")
    private String password;

    public void setInfo(String[] mailTos, String subject, String content) {
        setInfo(mailTos, subject, content, false, null);
    }

    public void setInfo(String[] mailTos, String subject, String content, Boolean isHTML) {
        setInfo(mailTos, subject, content, isHTML, null);
    }

    public void setInfo(String[] mailTos, String subject, String content, Boolean isHTML, String[] attach) {
        this.mailTos = mailTos;
        this.subject = subject;
        this.content = content;
        this.isHTML = isHTML;
        this.attach = attach;
    }

    public Properties getProperties() {
        Properties p = new Properties();
        p.put("mail.smtp.host", this.host);
        p.put("mail.smtp.port", this.port);
        p.put("mail.smtp.auth", String.valueOf(this.auth));
        return p;
    }

    public String[] getMailTos() { return mailTos; }

    public void setMailTos(String[] mailTos) { this.mailTos = mailTos; }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getHTML() {
        return isHTML;
    }

    public void setHTML(Boolean HTML) {
        isHTML = HTML;
    }

    public String[] getAttach() {
        return attach;
    }

    public void setAttach(String[] attach) {
        this.attach = attach;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public Boolean getAuth() { return auth; }

    public void setAuth(Boolean auth) { this.auth = auth; }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
