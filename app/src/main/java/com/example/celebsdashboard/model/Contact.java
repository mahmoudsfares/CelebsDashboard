package com.example.celebsdashboard.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/*
This class works with each JSONArray single component.
Usually the data that the user is interested in.
@SerializedName notations used to track the JSON object with
the string in arguments as key.
 */

public class Contact {
    @SerializedName("name")
    private String name;
    @SerializedName("email")
    private String email;
    @SerializedName("phone")
    private Numbers phone;

    public Contact(String name, Numbers phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Numbers getPhone() {
        return phone;
    }

    public void setPhone(Numbers phone) {
        this.phone = phone;
    }
}