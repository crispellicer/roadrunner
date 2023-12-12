package com.svalero.roadrunner.model.registrations;

import android.database.sqlite.SQLiteConstraintException;

import com.svalero.roadrunner.api.RoadRunnerApi;
import com.svalero.roadrunner.api.RoadRunnerApiInterface;
import com.svalero.roadrunner.contract.registrations.DeleteRegistrationContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteRegistrationModel implements DeleteRegistrationContract.Model {

    @Override
    public void deleteRegistration(long userId, OnDeleteRegistrationListener listener, long registrationId) {
        try {
            RoadRunnerApiInterface roadRunnerApi = RoadRunnerApi.buildInstance();
            Call<Void> callRegistrations = roadRunnerApi.deleteRegistration(registrationId);
            callRegistrations.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    listener.onDeleteSuccess();
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    String message = "Error invocando a la operación";
                    listener.onDeleteError(message);
                }
            });
        } catch (SQLiteConstraintException sce) {
            sce.printStackTrace();
        }
    }
}
