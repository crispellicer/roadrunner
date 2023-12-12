package com.svalero.roadrunner.contract.registrations;

public interface DeleteRegistrationContract {

    interface Model {
        interface OnDeleteRegistrationListener {
            void onDeleteSuccess();
            void onDeleteError(String message);
        }
        void deleteRegistration(long userId, OnDeleteRegistrationListener listener, long registrationId);
    }

    interface View {
        void showError(String errorMessage);
        void showMessage(String message);
    }

    interface Presenter {
        void deleteRegistration(long userId, long registrationId);
    }
}
