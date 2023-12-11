package com.svalero.roadrunner.presenter.registrations;

import com.svalero.roadrunner.contract.registrations.RegistrationsListContract;
import com.svalero.roadrunner.domain.Registration;
import com.svalero.roadrunner.model.registrations.RegistrationsListModel;

import java.util.List;

public class RegistrationsListPresenter implements RegistrationsListContract.Presenter,
        RegistrationsListContract.Model.OnLoadRegistrationsListener {

    private RegistrationsListModel model;
    private RegistrationsListContract.View view;

    public RegistrationsListPresenter(RegistrationsListContract.View view) {
        this.view = view;
        this.model = new RegistrationsListModel();
    }


    @Override
    public void loadRegistrationsByUser(long userId) {
        model.loadRegistrationsByUser(this, userId);
    }

    @Override
    public void onLoadRegistrationsSuccess(List<Registration> registrations) {
        view.showRegistrations(registrations);
    }

    @Override
    public void onLoadRegistrationsError(String message) {
        view.showMessage(message);
    }
}
