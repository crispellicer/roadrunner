package com.svalero.roadrunner.model.races;

import static com.svalero.roadrunner.db.Constants.DATABASE_NAME;

import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;

import androidx.room.Room;

import com.svalero.roadrunner.contract.races.AddFavRaceContract;
import com.svalero.roadrunner.db.AppDatabase;
import com.svalero.roadrunner.domain.FavRace;

public class AddFavRaceModel implements AddFavRaceContract.Model {

    private Context context;

    public AddFavRaceModel(Context context) {
        this.context = context;
    }

    @Override
    public boolean addFavRace(FavRace favRace) {
        try {
            final AppDatabase db = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries().build();
            db.favRaceDao().insert(favRace);
            return true;
        } catch (SQLiteConstraintException sce) {
            sce.printStackTrace();
            return false;
        }
    }

}
