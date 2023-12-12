package com.svalero.roadrunner.contract.registrations;

import com.svalero.roadrunner.domain.Registration;

import java.util.List;

public interface RegisterRegistrationContract {

    interface Model {
        interface OnRegisterRegistrationListener {
            void onRegisterSuccess(Registration registration);
            void onRegisterError(String message);
        }
        boolean registerRegistration(long raceId, long userId, OnRegisterRegistrationListener listener);
    }

    interface View {
        void showError(String errorMessage);
        void showMessage(String message);
        void resetForm();
    }

    interface Presenter {
        void registerRegistration(long raceId, long userId);
    }
}
