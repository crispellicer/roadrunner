package com.svalero.roadrunner.contract.races;

import com.svalero.roadrunner.domain.FavRace;

public interface AddFavRaceContract {

    interface Model {
        boolean addFavRace(FavRace favRace);
    }

    interface View {
        void showError(String errorMessage);

        void showMessage(String message);
    }

    interface Presenter {
        void addFavRace(FavRace favRace);

    }
}
