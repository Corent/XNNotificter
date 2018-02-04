package com.xnnotifiter.service.http;

import com.xnnotifiter.model.AlertManager;
import com.xnnotifiter.model.repos.PrometheusRepos;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PrometheusService {

    @Value("${prometheus.host}")
    private String host;

    public List<String> activeAlertmanagers() {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(host).addConverterFactory(GsonConverterFactory.create()).build();
        PrometheusApi service = retrofit.create(PrometheusApi.class);
        Call<PrometheusRepos> repos = service.listAlertManager();
        List<String> urls  = null;
        try {
            List<AlertManager> alertManagers = Optional.ofNullable(repos.execute())
                    .map(Response::body)
                    .map(PrometheusRepos::getData)
                    .map(PrometheusRepos.Data::getActiveAlertmanagers).orElse(new ArrayList<AlertManager>());
            urls = alertManagers.stream().map(AlertManager::getUrl).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return urls;
    }
}
