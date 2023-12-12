package com.svalero.roadrunner.presenter.users;

import com.svalero.roadrunner.contract.users.UserDetailsContract;
import com.svalero.roadrunner.domain.User;
import com.svalero.roadrunner.model.users.UserDetailsModel;
import com.svalero.roadrunner.view.users.UserDetailsView;

public class UserDetailsPresenter implements UserDetailsContract.Presenter,
    UserDetailsContract.Model.OnLoadUserListener {

    private UserDetailsModel model;
    private UserDetailsView view;

    public UserDetailsPresenter(UserDetailsView view) {
        this.view = view;
        this.model = new UserDetailsModel(view.getApplicationContext());
    }


    @Override
    public void loadDetailsUser(long userId) {
        model.loadDetailsUser(userId, this);
    }

    @Override
    public void onLoadUserSuccess(User user) {
        view.showUser(user);
    }

    @Override
    public void onLoadUserError(String message) {
        view.showMessage(message);
    }
}
