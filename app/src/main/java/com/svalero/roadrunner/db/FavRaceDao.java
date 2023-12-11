package com.svalero.roadrunner.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.svalero.roadrunner.domain.FavRace;

import java.util.List;

@Dao
public interface FavRaceDao {

    @Query(value = "SELECT * FROM FavRace")
    List<FavRace> getAll();

    @Insert
    void insert(FavRace favRace);

    @Delete
    void delete(FavRace favRace);

    @Update
    void update(FavRace favRace);
}
