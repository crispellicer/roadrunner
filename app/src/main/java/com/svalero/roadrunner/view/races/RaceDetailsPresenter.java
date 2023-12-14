package com.svalero.roadrunner.view.races;

import com.svalero.roadrunner.contract.races.RaceDetailsContract;
import com.svalero.roadrunner.domain.Race;
import com.svalero.roadrunner.model.races.RaceDetailsModel;
import com.svalero.roadrunner.view.races.RaceDetailsView;

public class RaceDetailsPresenter implements RaceDetailsContract.Presenter,
    RaceDetailsContract.Model.OnLoadRaceListener {

    private RaceDetailsModel model;
    private RaceDetailsView view;

    public RaceDetailsPresenter(RaceDetailsView view) {
        this.view = view;
        this.model = new RaceDetailsModel(view.getApplicationContext());
    }


    @Override
    public void loadDetailsRace(long raceId) {
        model.loadDetailsRace(raceId, this);
    }

    @Override
    public void onLoadRaceSuccess(Race race) {
        view.showRace(race);
    }

    @Override
    public void onLoadRaceError(String message) {
        view.showMessage(message);
    }
}
