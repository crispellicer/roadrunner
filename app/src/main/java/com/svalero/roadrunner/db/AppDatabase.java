package com.svalero.roadrunner.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.svalero.roadrunner.domain.FavRace;

@Database(entities ={FavRace.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract FavRaceDao favRaceDao();
}
