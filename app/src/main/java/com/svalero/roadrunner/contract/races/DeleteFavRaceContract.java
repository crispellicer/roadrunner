package com.svalero.roadrunner.contract.races;

import com.svalero.roadrunner.domain.FavRace;

public interface DeleteFavRaceContract {

    interface Model {
        boolean deleteFavRace(FavRace favRace);
    }

    interface View {
        void showMessage(String message);
    }

    interface Presenter {
        void deleteFavRace(FavRace favRace);

    }
}
