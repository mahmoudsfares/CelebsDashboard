package com.example.celebsdashboard.model;


import androidx.annotation.NonNull;

public class User {

    private String mUsername;
    private String mPassword;

    public User(String mUsername, String mPassword) {
        this.mUsername = mUsername;
        this.mPassword = mPassword;
    }

    public String getmUsername() {
        return mUsername;
    }

    public void setmUsername(String mUsername) {
        this.mUsername = mUsername;
    }

    public String getmPassword() {
        return mPassword;
    }

    public void setmPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    @NonNull
    @Override
    public String toString() {
        return "Username: " + getmUsername() + ", Password: " + getmPassword();
    }
}
