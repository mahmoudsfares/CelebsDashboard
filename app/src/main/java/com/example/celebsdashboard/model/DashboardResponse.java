package com.example.celebsdashboard.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/*
This class usually works with the root elements of the JSONResponse.
@SerializedName notations used to track the JSONObject with
the string in arguments as key.
 */

public class DashboardResponse {

    @SerializedName("contacts")
    private List<Contact> contacts;

    public DashboardResponse(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }
}
