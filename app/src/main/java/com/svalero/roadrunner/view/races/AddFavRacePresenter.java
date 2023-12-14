package com.svalero.roadrunner.view.races;

import com.svalero.roadrunner.adapter.FavRaceAdapter;
import com.svalero.roadrunner.adapter.RaceAdapter;
import com.svalero.roadrunner.contract.races.AddFavRaceContract;
import com.svalero.roadrunner.domain.FavRace;
import com.svalero.roadrunner.model.races.AddFavRaceModel;

public class AddFavRacePresenter implements AddFavRaceContract.Presenter {

    private AddFavRaceModel model;
    private RaceAdapter view;

    public AddFavRacePresenter(RaceAdapter view) {
        this.view = view;
        this.model = new AddFavRaceModel(view.getContext());
    }

    @Override
    public void addFavRace(FavRace favRace) {
        boolean done = model.addFavRace(favRace);
        if (done) {
            view.showMessage("Race added to favourites");
        } else {
            view.showError("An error success");
        }
    }
}
