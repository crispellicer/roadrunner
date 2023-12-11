package com.svalero.roadrunner.adapter;

import static com.svalero.roadrunner.db.Constants.DATABASE_NAME;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.svalero.roadrunner.R;
import com.svalero.roadrunner.contract.races.DeleteFavRaceContract;
import com.svalero.roadrunner.db.AppDatabase;
import com.svalero.roadrunner.domain.FavRace;

import java.util.List;

public class FavRaceAdapter extends RecyclerView.Adapter<FavRaceAdapter.FavRaceHolder> implements DeleteFavRaceContract.View{
    private Context context;
    private List<FavRace> favRacesList;
    private FavRace favRace;

    public FavRaceAdapter(Context context, List<FavRace> dataList) {
        this.context = context;
        this.favRacesList = dataList;

    }

    public Context getContext() {
        return context;
    }

    @Override
    public FavRaceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fav_race_item, parent, false);
        return new FavRaceHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavRaceHolder holder, int position) {

        holder.RaceName.setText(favRacesList.get(position).getName());
        holder.RaceType.setText(favRacesList.get(position).getType());
    }

    @Override
    public int getItemCount() {
        return favRacesList.size();
    }

    public void showMessage(String message) {

    }

    public void showError(String errorMessage) {

    }

    public class FavRaceHolder extends RecyclerView.ViewHolder {

        public TextView RaceName;
        public TextView RaceType;
        public Button deleteFavRaceButton;
        public View parentView;

        public FavRaceHolder(View view) {
            super(view);
            parentView = view;

            RaceName = view.findViewById((R.id.race_name));
            RaceType = view.findViewById(R.id.race_distance);
            deleteFavRaceButton = view.findViewById(R.id.delete_fav_race_button);

            deleteFavRaceButton.setOnClickListener(v -> deleteFavRace(getAdapterPosition()));
        }
    }

    private void deleteFavRace(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Are you sure?")
                .setTitle("Delete favourite race")
                .setPositiveButton("Yes", (dialog, id) -> {
                    final AppDatabase db = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME)
                            .allowMainThreadQueries().build();
                    FavRace favRace = favRacesList.get(position);
                    db.favRaceDao().delete(favRace);

                    favRacesList.remove(position);
                    notifyItemRemoved(position);
                })
                .setNegativeButton("No", (dialog, id) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}

