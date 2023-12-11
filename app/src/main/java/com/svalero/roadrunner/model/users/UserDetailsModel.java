package com.svalero.roadrunner.model.users;

import android.content.Context;

import com.svalero.roadrunner.api.RoadRunnerApi;
import com.svalero.roadrunner.api.RoadRunnerApiInterface;
import com.svalero.roadrunner.contract.users.UserDetailsContract;
import com.svalero.roadrunner.domain.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserDetailsModel implements UserDetailsContract.Model {

    private Context context;

    public UserDetailsModel(Context context) {
        this.context = context;
    }

    @Override
    public void loadDetailsUser(long userId, UserDetailsContract.Model.OnLoadUserListener listener) {


        RoadRunnerApiInterface roadRunnerApi = RoadRunnerApi.buildInstance();
        Call<User> callUsers = RoadRunnerApi.buildInstance().getUser(userId);
        callUsers.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                listener.onLoadUserSuccess(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                t.printStackTrace();
                String message = "Error invocando a la operación";
                listener.onLoadUserError(message);
            }
        });
    }
}
