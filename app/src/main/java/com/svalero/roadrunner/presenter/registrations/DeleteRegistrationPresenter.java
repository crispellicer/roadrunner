package com.svalero.roadrunner.presenter.registrations;

import com.svalero.roadrunner.adapter.RegistrationAdapter;
import com.svalero.roadrunner.contract.registrations.DeleteRegistrationContract;
import com.svalero.roadrunner.model.registrations.DeleteRegistrationModel;

public class DeleteRegistrationPresenter implements DeleteRegistrationContract.Presenter,
    DeleteRegistrationContract.Model.OnDeleteRegistrationListener {

    private DeleteRegistrationModel model;
    private RegistrationAdapter view;

    public DeleteRegistrationPresenter(RegistrationAdapter view) {
        model = new DeleteRegistrationModel();
        this.view = view;
    }

    @Override
    public void deleteRegistration(long registrationId, long userId) {
        model.deleteRegistration(userId, this, registrationId);
    }

    @Override
    public void onDeleteSuccess() {
        view.showMessage("Registration has been successfully removed");
    }

    @Override
    public void onDeleteError(String message) {
        view.showError("An error has occurred");
    }
}
