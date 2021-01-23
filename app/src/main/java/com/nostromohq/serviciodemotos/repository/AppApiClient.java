package com.nostromohq.serviciodemotos.repository;

import com.nostromohq.serviciodemotos.services.AppService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppApiClient {

    private static Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://sleepy-wildwood-42061.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static AppService getAppService() {
        return getRetrofit().create(AppService.class);
    }

}
