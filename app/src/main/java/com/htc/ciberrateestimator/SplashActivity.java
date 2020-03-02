package com.htc.ciberrateestimator;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                overridePendingTransition(0, 0);
                startActivity(new Intent(SplashActivity.this, NumberOfResourcesActivity.class));

            }
        }, 2000);
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
