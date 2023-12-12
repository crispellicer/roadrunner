package com.svalero.roadrunner.model.races;

import static com.svalero.roadrunner.db.Constants.DATABASE_NAME;

import android.content.Context;

import androidx.room.Room;

import com.svalero.roadrunner.contract.races.DeleteFavRaceContract;
import com.svalero.roadrunner.db.AppDatabase;
import com.svalero.roadrunner.domain.FavRace;

public class DeleteFavRaceModel implements DeleteFavRaceContract.Model {

    public Context context;

    public DeleteFavRaceModel(Context context) {
        this.context = context;
    }

    @Override
    public boolean deleteFavRace(FavRace favRace) {
        final AppDatabase db = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries().build();
        db.favRaceDao().delete(favRace);
        return true;
    }
}
