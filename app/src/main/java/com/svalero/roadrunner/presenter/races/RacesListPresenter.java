package com.svalero.roadrunner.presenter.races;

import com.svalero.roadrunner.contract.races.RacesListContract;
import com.svalero.roadrunner.domain.Race;
import com.svalero.roadrunner.model.races.RacesListModel;

import java.util.List;

public class RacesListPresenter implements RacesListContract.Presenter,
        RacesListContract.Model.OnLoadRacesListener{

    private RacesListModel model;
    private RacesListContract.View view;

    public RacesListPresenter(RacesListContract.View view) {
        this.view = view;
        this.model = new RacesListModel();
    }


    @Override
    public void loadAllRaces() {
        model.loadAllRaces(this);
    }

    @Override
    public void loadRacesByCity(String city) {

    }


    @Override
    public void onLoadRacesSuccess(List<Race> races) {
        view.showRaces(races);
    }

    @Override
    public void onLoadRacesError(String message) {
        view.showMessage(message);
    }
}
