package com.svalero.roadrunner.model.registrations;

import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;

import com.svalero.roadrunner.api.RoadRunnerApi;
import com.svalero.roadrunner.api.RoadRunnerApiInterface;
import com.svalero.roadrunner.contract.registrations.RegisterRegistrationContract;
import com.svalero.roadrunner.domain.Registration;
import com.svalero.roadrunner.domain.RegistrationRequestDto;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterRegistrationModel implements RegisterRegistrationContract.Model {

    private Context context;

    public RegisterRegistrationModel(Context context) {
        this.context = context;
    }

    @Override
    public boolean registerRegistration(long raceId, long userId, OnRegisterRegistrationListener listener) {
        try {
            RoadRunnerApiInterface roadRunnerApi = RoadRunnerApi.buildInstance();
            Call<Registration> callRegistrations = roadRunnerApi.addRegistration(raceId, userId);
            callRegistrations.enqueue(new Callback<Registration>() {
                @Override
                public void onResponse(Call<Registration> call, Response<Registration> response) {
                    Registration registration = response.body();
                    if (registration == null) {
                        listener.onRegisterError("");
                    } else {
                        listener.onRegisterSuccess(registration);
                    }
                }

                @Override
                public void onFailure(Call<Registration> call, Throwable t) {
                    String message = "Error invoking the operation";
                    listener.onRegisterError(message);
                }
            });
            return true;
        } catch (SQLiteConstraintException sce) {
            sce.printStackTrace();
            return false;
        }
    }
}

