package com.svalero.roadrunner.view.races;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.svalero.roadrunner.R;
import com.svalero.roadrunner.adapter.RaceAdapter;
import com.svalero.roadrunner.contract.races.RacesListContract;
import com.svalero.roadrunner.domain.Race;
import com.svalero.roadrunner.presenter.races.RacesListPresenter;
import com.svalero.roadrunner.view.MapsActivity;

import java.util.ArrayList;
import java.util.List;

public class RacesListView extends AppCompatActivity implements RacesListContract.View {

    private List<Race> racesList;

    private List<Race> racesListFilter;
    private RaceAdapter adapter;
    private RacesListPresenter presenter;
    private EditText etSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_races_list_view);

        presenter = new RacesListPresenter(this);

        etSearch  = findViewById(R.id.etSearch);
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                racesList.clear();
                racesList.addAll(racesListFilter);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                adapter.getFilter().filter(charSequence.toString().toLowerCase());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(etSearch.getWindowToken(), 0);
                    etSearch.setText("");
                    return true;
                } else {
                    return false;
                }
            }
        });

        initializeRecyclerView();
    }

    private void initializeRecyclerView() {
        racesList = new ArrayList<>();
        racesListFilter = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.races_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RaceAdapter(this, racesList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d("Races", "Llamada desde view");
        presenter.loadAllRaces();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_race, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.view_map) {
            Intent intent = new Intent(this, MapsActivity.class);
            startActivity(intent);
            return true;
        }
        return false;
    }

    @Override
    public void showRaces(List<Race> races) {
        racesList.clear();
        racesList.addAll(races);
        racesListFilter.addAll(races);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
