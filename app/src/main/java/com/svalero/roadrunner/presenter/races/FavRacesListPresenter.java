package com.svalero.roadrunner.presenter.races;

import com.svalero.roadrunner.contract.races.FavRacesListContract;
import com.svalero.roadrunner.domain.FavRace;
import com.svalero.roadrunner.model.races.FavRacesListModel;
import com.svalero.roadrunner.view.races.FavRacesListView;

import java.util.List;

public class FavRacesListPresenter implements FavRacesListContract.Presenter {

    private FavRacesListModel model;
    private FavRacesListView view;

    public FavRacesListPresenter(FavRacesListView view) {
        this.view = view;
        this.model = new FavRacesListModel(view.getApplicationContext());
    }

    @Override
    public void loadAllFavRaces() {
        List<FavRace> favRaces  = model.loadAllFavRaces();
        view.showFavRaces(favRaces);
    }
}
