package com.svalero.roadrunner.model.races;

import static com.svalero.roadrunner.db.Constants.DATABASE_NAME;

import android.content.Context;

import androidx.room.Room;

import com.svalero.roadrunner.contract.races.FavRacesListContract;
import com.svalero.roadrunner.db.AppDatabase;
import com.svalero.roadrunner.domain.FavRace;

import java.util.List;

public class FavRacesListModel implements FavRacesListContract.Model {

    private Context context;

    public FavRacesListModel(Context context) {
        this.context = context;
    }

    @Override
    public List<FavRace> loadAllFavRaces() {
        final AppDatabase db = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries().build();
        return db.favRaceDao().getAll();
    }
}
