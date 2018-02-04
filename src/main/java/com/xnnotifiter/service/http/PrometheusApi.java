package com.xnnotifiter.service.http;

import com.xnnotifiter.model.repos.PrometheusRepos;
import retrofit2.Call;
import retrofit2.http.GET;

public interface PrometheusApi {

    @GET("api/v1/alertmanagers")
    Call<PrometheusRepos> listAlertManager();
}
