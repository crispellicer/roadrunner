package com.svalero.roadrunner.contract.races;

import com.svalero.roadrunner.domain.FavRace;

import java.util.List;

public interface FavRacesListContract {

    interface Model {
        List<FavRace> loadAllFavRaces();
    }

    interface View {
        void showFavRaces(List<FavRace> favRaces);

        void showMessage(String message);
    }

    interface Presenter {
        void loadAllFavRaces();
    }
}
