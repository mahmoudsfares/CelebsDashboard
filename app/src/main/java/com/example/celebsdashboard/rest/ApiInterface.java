package com.example.celebsdashboard.rest;

import com.example.celebsdashboard.model.DashboardResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/*
This interface offers a blueprint for each data set you want returned, it provides keys for the values
that you provide in the called method.
 */

// the part of the link after the BASE_URL and before the ?api_key
// in the example below, the part ###THIS_PART###
public interface ApiInterface {
    // EXAMPLE: https://api.themoviedb.org/3/###THIS_PART###?api_key=53265ddd3b650dc47ec03e250ad91b90
    @GET(".")
    // by passing the key, the passed key will be preceded by the string "api_key"
    // both will come after ###THIS_PART###
    Call<DashboardResponse> getDashboard();
}
