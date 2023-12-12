package com.svalero.roadrunner.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.svalero.roadrunner.R;
import com.svalero.roadrunner.domain.Registration;
import com.svalero.roadrunner.presenter.registrations.DeleteRegistrationPresenter;

import java.util.List;

public class RegistrationAdapter extends RecyclerView.Adapter<RegistrationAdapter.RegistrationHolder> {

    private Context context;
    private List<Registration> registrationsList;
    private View snackBarView;

    private DeleteRegistrationPresenter presenter;

    public RegistrationAdapter(Context context, View view, List<Registration> dataList) {
        this.context = context;
        snackBarView = view;
        this.registrationsList = dataList;
        presenter = new DeleteRegistrationPresenter(this);
    }

    public Context getContext() {
        return context;
    }

    @Override
    public RegistrationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.registration_item, parent, false);
        return new RegistrationHolder(view);
    }

    @Override
    public void onBindViewHolder(RegistrationHolder holder, int position) {
        holder.registrationRaceName.setText(registrationsList.get(position).getRaceName());
        holder.registrationDistance.setText(registrationsList.get(position).getDistance());
        holder.registrationCity.setText(registrationsList.get(position).getCity());
    }

    @Override
    public int getItemCount() {
        return registrationsList.size();
    }

    public void showError(String errorMessage) {
        Snackbar.make(snackBarView, errorMessage,
                BaseTransientBottomBar.LENGTH_LONG).show();
    }

    public void showMessage(String message) {
        Snackbar.make(snackBarView, message,
                BaseTransientBottomBar.LENGTH_LONG).show();
    }

    public class RegistrationHolder extends RecyclerView.ViewHolder{

        public TextView registrationRaceName;
        public TextView registrationDistance;
        public TextView registrationCity;
        public Button deleteRegistrationButton;
        public View parentView;

        public RegistrationHolder(View view) {
            super(view);
            parentView = view;
            //snackBarView = parentView;

            registrationRaceName = view.findViewById(R.id.registration_race_name);
            registrationDistance = view.findViewById(R.id.registration_race_distance);
            registrationCity = view.findViewById(R.id.registration_race_city);
            deleteRegistrationButton = view.findViewById(R.id.delete_registration_button);

            deleteRegistrationButton.setOnClickListener(v -> deleteRegistration(getAdapterPosition()));

        }
    }

    private void deleteRegistration(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        SharedPreferences sharedPreferences = context.getSharedPreferences("mainSharedPreferences",Context.MODE_PRIVATE);
        long userId = sharedPreferences.getLong("userId", 0);
        builder.setMessage("Are you sure?")
                .setTitle("Delete registration")
                .setPositiveButton("Yes", (dialog, id) -> {
                    Registration registration = registrationsList.get(position);
                    presenter.deleteRegistration(registration.getId(), userId);

                    registrationsList.remove(position);
                    notifyItemRemoved(position);
                })
                .setNegativeButton("No", (dialog, id) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}

