package com.example.cheetahfoodordering.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.cheetahfoodordering.R;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void getStarted(View view) {
        startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));

    }
}