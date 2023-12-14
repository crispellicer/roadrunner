package com.svalero.roadrunner.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.svalero.roadrunner.R;
import com.svalero.roadrunner.domain.FavRace;
import com.svalero.roadrunner.domain.Race;
import com.svalero.roadrunner.view.races.AddFavRacePresenter;
import com.svalero.roadrunner.presenter.registrations.RegisterRegistrationPresenter;
import com.svalero.roadrunner.view.races.RaceDetailsView;

import java.util.ArrayList;
import java.util.List;

public class RaceAdapter extends RecyclerView.Adapter<RaceAdapter.RaceHolder> implements Filterable {

    private Context context;
    private List<Race> racesList;
    private View snackBarView;

    private RegisterRegistrationPresenter registrationPresenter;
    private AddFavRacePresenter favRacePresenter;

    public RaceAdapter(Context context, List<Race> dataList) {
        this.context = context;
        this.racesList = dataList;
        favRacePresenter = new AddFavRacePresenter(this);
        registrationPresenter = new RegisterRegistrationPresenter(this);
    }

    public Context getContext() {
        return context;
    }

    @Override
    public RaceHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.race_item, parent, false);
        return new RaceHolder(view);
    }

    @Override
    public void onBindViewHolder(RaceHolder holder, int position) {
        holder.raceName.setText(racesList.get(position).getName());
        holder.raceDistance.setText(racesList.get(position).getDistance());
    }

    @Override
    public int getItemCount() {
        return racesList.size();
    }

    public void showError(String errorMessage) {
        Snackbar.make(snackBarView, errorMessage,
                BaseTransientBottomBar.LENGTH_LONG).show();
    }

    public void showMessage(String message) {
        Snackbar.make(snackBarView, message,
                BaseTransientBottomBar.LENGTH_LONG).show();
    }

    @Override
    public Filter getFilter() {
        return new RaceFilter();
    }

    public class RaceHolder extends RecyclerView.ViewHolder{

        public TextView raceName;
        public TextView raceDistance;
        public Button raceRegistrationButton;
        public Button raceDetailsButton;
        public Button addFavRaceButton;
        public View parentView;

        public RaceHolder(View view) {
            super(view);
            parentView = view;
            snackBarView = parentView;

            raceName = view.findViewById(R.id.race_name);
            raceDistance = view.findViewById(R.id.race_distance);
            raceRegistrationButton = view.findViewById(R.id.race_registration_button);
            raceDetailsButton = view.findViewById(R.id.race_details_button);
            addFavRaceButton = view.findViewById(R.id.add_fav_race_button);

            raceRegistrationButton.setOnClickListener(v -> raceRegister(getAdapterPosition()));
            raceDetailsButton.setOnClickListener(v -> seeDetails(getAdapterPosition()));
            addFavRaceButton.setOnClickListener(v -> addFavRace(getAdapterPosition()));
        }
    }

    private void raceRegister(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        SharedPreferences sharedPreferences = context.getSharedPreferences("mainSharedPreferences",Context.MODE_PRIVATE);
        long userId = sharedPreferences.getLong("userId", 0);
        builder.setMessage("Are you sure?")
                .setTitle("Register")
                .setPositiveButton("Yes", (dialog, id) -> {
                    Race race = racesList.get(position);
                    registrationPresenter.registerRegistration(race.getId(),userId);
                })
                .setNegativeButton("No", (dialog, id) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void seeDetails(int position) {
        Race race = racesList.get(position);

        Intent intent = new Intent(context, RaceDetailsView.class);
        intent.putExtra("raceId", race.getId());
        intent.putExtra("name", race.getName());
        context.startActivity(intent);
    }

    private void addFavRace(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Do you want to add to favourites?")
                .setTitle("Add to Favourites")
                .setPositiveButton("Yes", (dialog, id) -> {
                    Race race = racesList.get(position);

                    FavRace favRace = new FavRace();
                    favRace.setName(race.getName());
                    favRace.setDistance(race.getDistance());
                    favRace.setType(race.getType());
                    favRace.setCity(race.getCity());
                    favRace.setRegistrationPrice(race.getRegistrationPrice());
                    favRace.setRaceDate(race.getRaceDate());
                    favRace.setLongitude(race.getLongitude());
                    favRace.setLatitude(race.getLatitude());

                    favRacePresenter.addFavRace(favRace);
                })
                .setNegativeButton("No", (dialog, id) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    class RaceFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Race> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(racesList);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Race race : racesList) {
                    if (race.getName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(race);
                    }
                    if (race.getDistance().toLowerCase().contains(filterPattern)) {
                        filteredList.add(race);
                    }
                    if (race.getCity().toLowerCase().contains(filterPattern)) {
                        filteredList.add(race);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            racesList.clear();
            racesList.addAll((List)results.values);
            notifyDataSetChanged();
        }
    }
}

