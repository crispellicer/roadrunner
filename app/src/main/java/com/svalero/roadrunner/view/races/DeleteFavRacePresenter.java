package com.svalero.roadrunner.view.races;

import com.svalero.roadrunner.adapter.FavRaceAdapter;
import com.svalero.roadrunner.contract.races.DeleteFavRaceContract;
import com.svalero.roadrunner.domain.FavRace;
import com.svalero.roadrunner.model.races.DeleteFavRaceModel;

public class DeleteFavRacePresenter implements DeleteFavRaceContract.Presenter {

    private DeleteFavRaceModel model;
    private FavRaceAdapter view;

    public DeleteFavRacePresenter(DeleteFavRaceModel model, FavRaceAdapter view) {
        this.model = new DeleteFavRaceModel(view.getContext());
        this.view = view;
    }

    @Override
    public void deleteFavRace(FavRace favRace) {
        model.deleteFavRace(favRace);
    }
}
