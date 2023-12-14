package com.svalero.roadrunner.view.races;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.svalero.roadrunner.R;
import com.svalero.roadrunner.adapter.FavRaceAdapter;
import com.svalero.roadrunner.contract.races.FavRacesListContract;
import com.svalero.roadrunner.domain.FavRace;

import java.util.ArrayList;
import java.util.List;

public class FavRacesListView extends AppCompatActivity implements FavRacesListContract.View {

    private List<FavRace> favRacesList;
    private FavRaceAdapter adapter;
    private FavRacesListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_races_list_view);

        presenter = new FavRacesListPresenter(this);

        initializeRecyclerView();
    }

    private void initializeRecyclerView() {
        favRacesList = new ArrayList<>();

        RecyclerView recyclerView;
        recyclerView = findViewById(R.id.fav_races_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new FavRaceAdapter(this, favRacesList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        presenter.loadAllFavRaces();

    }

    public void showFavRaces(List<FavRace> favRaces) {
        favRacesList.clear();
        favRacesList.addAll(favRaces);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();

    }
}
