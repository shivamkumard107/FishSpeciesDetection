package com.myntra.android.speciesdetectionandroid.auth;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.myntra.android.speciesdetectionandroid.R;


public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new LoginOption()).commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        moveTaskToBack(true);
    }
}