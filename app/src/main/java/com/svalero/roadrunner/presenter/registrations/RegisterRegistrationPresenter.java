package com.svalero.roadrunner.presenter.registrations;

import android.content.Context;

import com.svalero.roadrunner.adapter.RaceAdapter;
import com.svalero.roadrunner.contract.registrations.RegisterRegistrationContract;
import com.svalero.roadrunner.domain.Registration;
import com.svalero.roadrunner.model.registrations.RegisterRegistrationModel;

public class RegisterRegistrationPresenter implements RegisterRegistrationContract.Presenter,
    RegisterRegistrationContract.Model.OnRegisterRegistrationListener{

    private RegisterRegistrationModel model;
    private RaceAdapter view;
    private Context context;

    public RegisterRegistrationPresenter(RaceAdapter view) {
        model = new RegisterRegistrationModel(context);
        this.view = view;
    }

    @Override
    public void registerRegistration(long raceId, long userId) {
        model.registerRegistration(raceId, userId, this);
    }

    @Override
    public void onRegisterSuccess(Registration registration) {
        view.showMessage("You have been registered!");
    }

    @Override
    public void onRegisterError(String message) {
        view.showError("You are already registered in this race!");
    }
}

