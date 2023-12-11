package com.svalero.roadrunner.contract.races;

import com.svalero.roadrunner.domain.Race;

public interface RaceDetailsContract {

    interface Model {
        interface OnLoadRaceListener {
            void onLoadRaceSuccess(Race race);
            void onLoadRaceError(String message);
        }
        void loadDetailsRace(long raceId, RaceDetailsContract.Model.OnLoadRaceListener listener);
    }


    interface View {
        void showRace(Race race);
        void showMessage(String message);
    }

    interface Presenter {
        void loadDetailsRace(long raceId);
    }
}
