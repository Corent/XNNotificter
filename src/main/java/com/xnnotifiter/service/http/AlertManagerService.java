package com.xnnotifiter.service.http;

import com.xnnotifiter.model.Alert;
import com.xnnotifiter.model.repos.AlertRepos;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlertManagerService {

    public List<Alert> alerts(String host) {

        host = host.replaceAll("api.*$", "");
        Retrofit retrofit = new Retrofit.Builder().baseUrl(host).addConverterFactory(GsonConverterFactory.create()).build();
        AlertManagerApi service = retrofit.create(AlertManagerApi.class);
        Call<AlertRepos> repos = service.listAlerts();
        List<Alert> alerts  = null;
        try {
            alerts = Optional.ofNullable(repos.execute())
                    .map(Response::body)
                    .map(AlertRepos::getData)
                    .orElse(new ArrayList<Alert>());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return alerts;
    }

    public Map<String, List<Alert>> alerts(List<String> hosts) {
        return hosts.parallelStream()
                .map(host -> new HostAlerts(host, alerts(host)))
                .collect(Collectors.toMap(HostAlerts::getHost, HostAlerts::getAlerts));
    }

    private static class HostAlerts {

        private String host;
        private List<Alert> alerts;

        public HostAlerts(String host, List<Alert> alerts) {
            this.host = host;
            this.alerts = alerts;
        }

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public List<Alert> getAlerts() {
            return alerts;
        }

        public void setAlerts(List<Alert> alerts) {
            this.alerts = alerts;
        }
    }
}
