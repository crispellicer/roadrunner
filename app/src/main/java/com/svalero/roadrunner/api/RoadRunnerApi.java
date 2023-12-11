package com.svalero.roadrunner.api;

import static com.svalero.roadrunner.api.Constants.BASE_URL;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RoadRunnerApi {

    public static RoadRunnerApiInterface buildInstance() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(RoadRunnerApiInterface.class);
    }
}
