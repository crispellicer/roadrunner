package com.svalero.roadrunner.view.users;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.svalero.roadrunner.R;
import com.svalero.roadrunner.contract.users.UserDetailsContract;
import com.svalero.roadrunner.domain.User;
import com.svalero.roadrunner.presenter.users.UserDetailsPresenter;

public class UserDetailsView extends AppCompatActivity implements UserDetailsContract.View {

    private User user;
    private UserDetailsPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details_view);

        presenter = new UserDetailsPresenter(this);
        SharedPreferences sharedPreferences = getSharedPreferences("mainSharedPreferences", Context.MODE_PRIVATE);
        long userId = sharedPreferences.getLong("userId", 0);

        presenter.loadDetailsUser(userId);
    }

    @Override
    public void showUser(User user) {
        TextView etUserName = findViewById(R.id.tv_user_name_details);
        TextView etUserSurname = findViewById(R.id.tv_user_surname_details);
        TextView etUserTelephone = findViewById(R.id.tv_user_telephone_details);
        TextView etUserBirthDate = findViewById(R.id.tv_user_birthdate_details);

        etUserName.setText(user.getName());
        etUserSurname.setText(user.getSurname());
        etUserTelephone.setText(user.getTelephone());
        etUserBirthDate.setText(user.getBirthDate());
    }

    @Override
    public void showMessage(String message) {

    }
}
