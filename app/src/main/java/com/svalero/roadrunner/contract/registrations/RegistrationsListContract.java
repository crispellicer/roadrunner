package com.svalero.roadrunner.contract.registrations;

import com.svalero.roadrunner.contract.races.RacesListContract;
import com.svalero.roadrunner.domain.Race;
import com.svalero.roadrunner.domain.Registration;

import java.util.List;

public interface RegistrationsListContract {

    interface Model {
        interface OnLoadRegistrationsListener {
            void onLoadRegistrationsSuccess(List<Registration> registration);
            void onLoadRegistrationsError(String message);
        }
        void loadRegistrationsByUser(RegistrationsListContract.Model.OnLoadRegistrationsListener listener, long userId);
    }

    interface View {
        void showRegistrations(List<Registration> registrations);

        void showMessage(String message);
    }

    interface Presenter {
        void loadRegistrationsByUser(long userId);
    }
}

