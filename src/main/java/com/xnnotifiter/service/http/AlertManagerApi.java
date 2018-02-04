package com.xnnotifiter.service.http;

import com.xnnotifiter.model.repos.AlertRepos;
import retrofit2.Call;
import retrofit2.http.GET;

public interface AlertManagerApi {

    @GET("api/v1/alerts")
    Call<AlertRepos> listAlerts();
}
