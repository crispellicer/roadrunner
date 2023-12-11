package com.svalero.roadrunner.model.registrations;

import android.util.Log;

import com.svalero.roadrunner.api.RoadRunnerApi;
import com.svalero.roadrunner.api.RoadRunnerApiInterface;
import com.svalero.roadrunner.contract.registrations.RegistrationsListContract;
import com.svalero.roadrunner.domain.Registration;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationsListModel implements RegistrationsListContract.Model {


    @Override
    public void loadRegistrationsByUser(OnLoadRegistrationsListener listener, long userId) {
        RoadRunnerApiInterface roadRunnerApi = RoadRunnerApi.buildInstance();
        Call<List<Registration>> callRaces = roadRunnerApi.getRegistrationsByUser(userId);
        Log.d("Registration", "Llamada desde model");
        callRaces.enqueue(new Callback<List<Registration>>() {
            @Override
            public void onResponse(Call<List<Registration>> call, Response<List<Registration>> response) {
                Log.d("Registration", "Llamada desde model ok");
                List<Registration> registrations = response.body();
                listener.onLoadRegistrationsSuccess(registrations);
            }

            @Override
            public void onFailure(Call<List<Registration>> call, Throwable t) {
                Log.d("Registration", "Llamada desde model error");
                t.printStackTrace();
                String message = "Error invocando a la operación";
                listener.onLoadRegistrationsError(message);
            }
        });
    }
}
