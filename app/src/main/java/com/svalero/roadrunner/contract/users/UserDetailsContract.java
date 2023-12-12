package com.svalero.roadrunner.contract.users;

import com.svalero.roadrunner.domain.User;

public interface UserDetailsContract {

    interface Model {
        interface OnLoadUserListener {
            void onLoadUserSuccess(User user);
            void onLoadUserError(String message);
        }
        void loadDetailsUser(long userId, UserDetailsContract.Model.OnLoadUserListener listener);
    }


    interface View {
        void showUser(User user);
        void showMessage(String message);
    }

    interface Presenter {
        void loadDetailsUser(long userId);
    }
}
