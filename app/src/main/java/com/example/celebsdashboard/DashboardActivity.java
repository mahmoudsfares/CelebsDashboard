package com.example.celebsdashboard;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.celebsdashboard.adapter.DashboardAdapter;
import com.example.celebsdashboard.model.Contact;
import com.example.celebsdashboard.model.DashboardResponse;
import com.example.celebsdashboard.rest.ApiClient;
import com.example.celebsdashboard.rest.ApiInterface;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Url;

public class DashboardActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    // upper text that welcomes the user
    private TextView mWelcomeTv;

    // list that shows contacts
    private RecyclerView mRecyclerView;

    // custom adapter for the recyclerview's views
    private DashboardAdapter mContactAdapter;

    // text that shows in case of no contacts
    private TextView mErrorMessageDisplay;

    // spinner that moves while loading
    private ProgressBar mLoadingIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // welcome message with user's name
        mWelcomeTv = findViewById(R.id.welcome_tv);
        // set welcome message text to username
        Intent i = getIntent();
        mWelcomeTv.setText("Welcome, " + i.getStringExtra("username"));

        // setting sign out button functionality
        Button signout = findViewById(R.id.signout_button);
        signout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // special intent that indicates that the user got back to the main activity by signing out, not by hitting back button
                Intent bi = new Intent(DashboardActivity.this, MainActivity.class);
                // empty out the fields if the user signed out to protect privacy
                bi.putExtra("username", "");
                bi.putExtra("password", "");
                startActivity(bi);
            }
        });

       mLoadingIndicator = (ProgressBar) findViewById(R.id.loading_spinner);
       mErrorMessageDisplay = (TextView) findViewById(R.id.no_results);

        mRecyclerView = (RecyclerView) findViewById(R.id.contacts_list);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new DashboardAdapter());

        // only loading indicator is visible
        setUIInitialCase();

        // instance to build the url and create a Retrofit object "ApiClient.getClient()"
        // RetrofitObject.create(ServiceClass.class) returns an ApiInterface object
        // that is used to override the interface methods of what you want to do
        // "in this case getting the top rated movies"
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<DashboardResponse> call = apiService.getDashboard();
        call.enqueue(new Callback<DashboardResponse>() {
            @Override
            public void onResponse(Call<DashboardResponse> call, Response<DashboardResponse> response) {
                int statusCode = response.code();
                List<Contact> contacts = response.body().getContacts();
                if(contacts == null) showErrorMessage();
                else showData();
                mContactAdapter = new DashboardAdapter();
                mContactAdapter.setContactData((ArrayList<Contact>) contacts);
                mRecyclerView.setAdapter(mContactAdapter);
            }

            @Override
            public void onFailure(Call<DashboardResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }

    private void setUIInitialCase(){
        mLoadingIndicator.setVisibility(View.VISIBLE);
        mErrorMessageDisplay.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.INVISIBLE);
    }

    private void showErrorMessage(){
        mLoadingIndicator.setVisibility(View.INVISIBLE);
        mErrorMessageDisplay.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.INVISIBLE);
    }

    private void showData(){
        mLoadingIndicator.setVisibility(View.INVISIBLE);
        mErrorMessageDisplay.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }
}