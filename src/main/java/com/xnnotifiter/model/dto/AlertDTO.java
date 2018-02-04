package com.xnnotifiter.model.dto;

import java.util.Date;

public class AlertDTO {

    private Integer id;
    private String fingerprint;
    private String alertname;
    private String state;
    private String json;
    private Date ctime;
    private Date utime;

    public AlertDTO() { }

    public AlertDTO(Integer id, String fingerprint, String alertname, String state, String json, Date ctime, Date utime) {
        this.id = id;
        this.fingerprint = fingerprint;
        this.alertname = alertname;
        this.state = state;
        this.json = json;
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

    public String getAlertname() {
        return alertname;
    }

    public void setAlertname(String alertname) {
        this.alertname = alertname;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
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
