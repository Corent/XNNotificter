package com.xnnotifiter.model.repos;

import com.xnnotifiter.model.Alert;

import java.util.List;

public class AlertRepos {

    private String status;
    private List<Alert> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Alert> getData() {
        return data;
    }

    public void setData(List<Alert> data) {
        this.data = data;
    }
}
