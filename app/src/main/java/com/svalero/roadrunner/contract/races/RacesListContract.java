package com.svalero.roadrunner.contract.races;

import com.svalero.roadrunner.domain.Race;

import java.util.List;

public interface RacesListContract {

    interface Model {
        interface OnLoadRacesListener {
            void onLoadRacesSuccess(List<Race> races);
            void onLoadRacesError(String message);
        }
        void loadAllRaces(RacesListContract.Model.OnLoadRacesListener listener);
        List<Race> loadRacesByCity(String city);
    }

    interface View {
        void showRaces(List<Race> races);

        void showMessage(String message);
    }

    interface Presenter {
        void loadAllRaces();
        void loadRacesByCity(String city);
    }
}
