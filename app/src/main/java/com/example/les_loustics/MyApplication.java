package com.example.les_loustics;

import android.app.Application;

import com.example.les_loustics.db.User;

public class MyApplication extends Application {

    private User currentUser;

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User user) {
        currentUser = user;
    }

}
