package com.svalero.roadrunner.model.races;

import android.util.Log;

import com.svalero.roadrunner.api.RoadRunnerApi;
import com.svalero.roadrunner.api.RoadRunnerApiInterface;
import com.svalero.roadrunner.contract.races.RacesListContract;
import com.svalero.roadrunner.domain.Race;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RacesListModel implements RacesListContract.Model {


    @Override
    public void loadAllRaces(OnLoadRacesListener listener) {
        RoadRunnerApiInterface roadRunnerApi = RoadRunnerApi.buildInstance();
        Call<List<Race>> callRaces = roadRunnerApi.getRaces();
        Log.d("Races", "Llamada desde model");
        callRaces.enqueue(new Callback<List<Race>>() {
            @Override
            public void onResponse(Call<List<Race>> call, Response<List<Race>> response) {
                Log.d("Races", "Llamada desde model ok");
                List<Race> races = response.body();
                listener.onLoadRacesSuccess(races);

            }

            @Override
            public void onFailure(Call<List<Race>> call, Throwable t) {
                Log.d("Races", "Llamada desde model error");
                t.printStackTrace();
                String message = "Error invocando a la operación";
                listener.onLoadRacesError(message);
            }
        });
    }

    @Override
    public List<Race> loadRacesByCity(String city) {
        return null;
    }

}
