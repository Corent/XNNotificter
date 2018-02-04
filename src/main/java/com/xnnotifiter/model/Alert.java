package com.xnnotifiter.model;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public class Alert {

    private Labels labels;
    private Annotations annotations;
    private String startsAt;
    private String endsAt;
    private String generatorURL;
    private Status status;
    private List<String> receivers;
    private String fingerprint;

    public Labels getLabels() {
        return labels;
    }

    public void setLabels(Labels labels) {
        this.labels = labels;
    }

    public Annotations getAnnotations() {
        return annotations;
    }

    public void setAnnotations(Annotations annotations) {
        this.annotations = annotations;
    }

    public String getStartsAt() {
        return startsAt;
    }

    public void setStartsAt(String startsAt) {
        this.startsAt = startsAt;
    }

    public String getEndsAt() {
        return endsAt;
    }

    public void setEndsAt(String endsAt) {
        this.endsAt = endsAt;
    }

    public String getGeneratorURL() {
        return generatorURL;
    }

    public void setGeneratorURL(String generatorURL) {
        this.generatorURL = generatorURL;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<String> getReceivers() {
        return receivers;
    }

    public void setReceivers(List<String> receivers) {
        this.receivers = receivers;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }

    public static class Labels {

        private String alertname;
        private String exported_job;
        private String instance;
        private String job;
        private String monitor;
        private String severity;

        public String getAlertname() {
            return alertname;
        }

        public void setAlertname(String alertname) {
            this.alertname = alertname;
        }

        public String getExported_job() {
            return exported_job;
        }

        public void setExported_job(String exported_job) {
            this.exported_job = exported_job;
        }

        public String getInstance() {
            return instance;
        }

        public void setInstance(String instance) {
            this.instance = instance;
        }

        public String getJob() {
            return job;
        }

        public void setJob(String job) {
            this.job = job;
        }

        public String getMonitor() {
            return monitor;
        }

        public void setMonitor(String monitor) {
            this.monitor = monitor;
        }

        public String getSeverity() {
            return severity;
        }

        public void setSeverity(String severity) {
            this.severity = severity;
        }
    }

    public static class Annotations {

        private String description;
        private String summary;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        @Override
        public String toString() {
            return JSONObject.toJSONString(this);
        }
    }

    public static class Status {

        private String state;
        private List<?> silencedBy;
        private List<?> inhibitedBy;

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public List<?> getSilencedBy() {
            return silencedBy;
        }

        public void setSilencedBy(List<?> silencedBy) {
            this.silencedBy = silencedBy;
        }

        public List<?> getInhibitedBy() {
            return inhibitedBy;
        }

        public void setInhibitedBy(List<?> inhibitedBy) {
            this.inhibitedBy = inhibitedBy;
        }
    }
}