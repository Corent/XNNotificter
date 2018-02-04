package com.xnnotifiter.model.dto;

import java.util.Date;

public class SMS {

    private Integer id;
    private String fingerprint;
    private String content;
    private Boolean sent;
    private Date ctime;
    private Date utime;

    public SMS(String fingerprint, String content) {
        this.fingerprint = fingerprint;
        this.content = content;
        this.sent = false;
    }

    public SMS() { }

    public SMS(Integer id, String fingerprint, String content, Boolean sent, Date ctime, Date utime) {
        this.id = id;
        this.fingerprint = fingerprint;
        this.content = content;
        this.sent = sent;
        this.ctime = ctime;
        this.utime = utime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getSent() {
        return sent;
    }

    public void setSent(Boolean sent) {
        this.sent = sent;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Date getUtime() {
        return utime;
    }

    public void setUtime(Date utime) {
        this.utime = utime;
    }
}
