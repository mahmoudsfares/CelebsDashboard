package com.example.celebsdashboard;


import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.celebsdashboard.model.User;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();


    // broadcast for tracking battery status (level, charging or not)
    private BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context ctxt, Intent intent) {
            // battery level
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);

            // battery charging or not
            IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
            Intent batteryStatus = ctxt.registerReceiver(null, ifilter);
            int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
            boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING || status == BatteryManager.BATTERY_STATUS_FULL;

            // show a toast message if battery is less than 20% and not charging
            if(level < 20 && !isCharging)
                Toast.makeText(MainActivity.this,"WARNING: Battery low",Toast.LENGTH_SHORT).show();
        }
    };

    private EditText mLoginUsernameET;
    private EditText mLoginPasswordET;
    private TextView mSignupTV;

    @Override
    public void onBackPressed() {
        // clear username and password fields before closing the app
        mLoginUsernameET.setText("");
        mLoginPasswordET.setText("");

        // clear the activity stack to close the app when back pressed in the MainActivity
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.registerReceiver(this.mBatInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

        mLoginUsernameET = findViewById(R.id.login_username_et);
        mLoginPasswordET = findViewById(R.id.login_password_et);
        mLoginPasswordET.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        mSignupTV = findViewById(R.id.signup_tv);
        mSignupTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SignupActivity.class));
            }
        });

        // back intent to detect when this activity was accessed by signing out or not
        Intent bi = getIntent();
        // if not accessed by signing out (just by hitting back button)
        if(bi != null){
            mLoginUsernameET.setText("");
            mLoginPasswordET.setText("");
        }

        Button login = findViewById(R.id.login_button);
        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(checkEntryData(mLoginUsernameET,mLoginPasswordET)){
                    String welcome = mLoginUsernameET.getText().toString().trim();
                    Intent i = new Intent(MainActivity.this, DashboardActivity.class);
                    i.putExtra("username",welcome);

                    startActivity(i);
                }
                else{
                    Toast.makeText(MainActivity.this, R.string.wrong_login_entry, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    // checks the username and password
    private boolean checkEntryData(EditText usernameET, EditText passwordET){

        String loginUsername = usernameET.getText().toString().trim();
        String loginPassword = passwordET.getText().toString();
        boolean correctUserdata = false;
        SignupActivity signupActivity = new SignupActivity();
        List<User> users = signupActivity.loadData(this);
        if(users == null)
            return false;

        else{
            for(User s: users){
                if(loginUsername.equals(s.getmUsername()) && loginPassword.equals((s.getmPassword()))){
                    correctUserdata = true;
                    break;
                }
            }
        }
        return (correctUserdata);
    }

}
