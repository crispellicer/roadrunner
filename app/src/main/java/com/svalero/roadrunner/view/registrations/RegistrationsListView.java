package com.svalero.roadrunner.view.registrations;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.svalero.roadrunner.R;
import com.svalero.roadrunner.adapter.RegistrationAdapter;
import com.svalero.roadrunner.contract.registrations.RegistrationsListContract;
import com.svalero.roadrunner.domain.Registration;
import com.svalero.roadrunner.presenter.registrations.RegistrationsListPresenter;
import com.svalero.roadrunner.view.users.UserDetailsView;

import java.util.ArrayList;
import java.util.List;

public class RegistrationsListView extends AppCompatActivity implements RegistrationsListContract.View{

    private List<Registration> registrationsList;
    private RegistrationAdapter adapter;
    private RegistrationsListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrations_list_view);

        presenter = new RegistrationsListPresenter(this);

        initializeRecyclerView();
    }

    private void initializeRecyclerView() {
        registrationsList = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.registrations_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RegistrationAdapter(this, this.findViewById(android.R.id.content), registrationsList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d("Registrations", "Llamada desde view");
        presenter.loadRegistrationsByUser(1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_myprof, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.my_info) {
            Intent intent = new Intent(this, UserDetailsView.class);
            startActivity(intent);
            return true;
        }
        return false;
    }

    @Override
    public void showRegistrations(List<Registration> registrations) {
        registrationsList.clear();
        registrationsList.addAll(registrations);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
