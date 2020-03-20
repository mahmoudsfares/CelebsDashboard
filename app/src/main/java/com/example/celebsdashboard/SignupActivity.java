package com.example.celebsdashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.celebsdashboard.model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class SignupActivity extends AppCompatActivity {

    ArrayList<User> users;
    private EditText usernameET;
    private EditText passwordET;
    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        users = loadData(this);
        usernameET = findViewById(R.id.signup_username_et);
        passwordET = findViewById(R.id.signup_password_et);
        passwordET.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        save = findViewById(R.id.save_user_data);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newUsername = usernameET.getText().toString().trim();
                String newPassword = passwordET.getText().toString();
                saveData(newUsername, newPassword);
            }
        });
    }

    private void saveData(String username, String password) {

        if(users == null)
            users = new ArrayList<>();
        users.add(new User(username, password));
        SharedPreferences sharedPreferences = getSharedPreferences("User", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(users);
        editor.putString("userList", json);
        editor.apply();
        Toast.makeText(this,"Saved", Toast.LENGTH_SHORT).show();
        finish();
    }

    public ArrayList<User> loadData(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("User", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("userList", null);
        Type type = new TypeToken<ArrayList<User>>() {}.getType();
        users = gson.fromJson(json, type);
        return users;
    }
}