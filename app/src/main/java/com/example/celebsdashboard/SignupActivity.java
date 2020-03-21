package com.example.celebsdashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.celebsdashboard.data.DatabaseClient;
import com.example.celebsdashboard.model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class SignupActivity extends AppCompatActivity {

    private EditText usernameET;
    private EditText passwordET;
    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        usernameET = findViewById(R.id.signup_username_et);
        passwordET = findViewById(R.id.signup_password_et);
        passwordET.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        save = findViewById(R.id.save_user_data);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
    }

    private void saveData() {
        final String sUsername = usernameET.getText().toString().trim();
        final String sPassword = passwordET.getText().toString();

        if (sUsername.isEmpty()) {
            usernameET.setError("Username required");
            usernameET.requestFocus();
            return;
        }

        if (sPassword.isEmpty()) {
            passwordET.setError("Password required");
            passwordET.requestFocus();
            return;
        }

        class SaveUser extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                //creating a task
                User user = new User(sUsername, sPassword);

                //adding to database
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .userDao()
                        .insert(user);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            }
        }
        SaveUser su = new SaveUser();
        su.execute();
    }
}


