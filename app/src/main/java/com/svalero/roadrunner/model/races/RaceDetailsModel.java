package com.svalero.roadrunner.model.races;

import android.content.Context;

import com.svalero.roadrunner.api.RoadRunnerApi;
import com.svalero.roadrunner.api.RoadRunnerApiInterface;
import com.svalero.roadrunner.contract.races.RaceDetailsContract;
import com.svalero.roadrunner.domain.Race;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RaceDetailsModel implements RaceDetailsContract.Model {

    private Context context;

    public RaceDetailsModel(Context context) {
        this.context = context;
    }

    @Override
    public void loadDetailsRace(long raceId, OnLoadRaceListener listener) {


        RoadRunnerApiInterface roadRunnerApi = RoadRunnerApi.buildInstance();
        Call<Race> callRaces = RoadRunnerApi.buildInstance().getRace(raceId);
        callRaces.enqueue(new Callback<Race>() {
            @Override
            public void onResponse(Call<Race> call, Response<Race> response) {
                listener.onLoadRaceSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Race> call, Throwable t) {
                t.printStackTrace();
                String message = "Error invocando a la operación";
                listener.onLoadRaceError(message);
            }
        });
    }
}
