package com.example.celebsdashboard.rest;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/*
This class' getClient() method takes the outcome of the ApiInterface
methods and builds the link, then returns a Retrofit object to retrieve
requested data.
 */

public class ApiClient {

    public static final String BASE_URL = "https://api.androidhive.info/contacts/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
