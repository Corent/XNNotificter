package com.xnnotifiter.model.repos;

import com.xnnotifiter.model.AlertManager;

import java.util.List;

public class PrometheusRepos {

    private String status;
    private Data data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data {

        private List<AlertManager> activeAlertmanagers;

        public List<AlertManager> getActiveAlertmanagers() {
            return activeAlertmanagers;
        }

        public void setActiveAlertmanagers(List<AlertManager> activeAlertmanagers) {
            this.activeAlertmanagers = activeAlertmanagers;
        }
    }
}
