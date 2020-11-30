package com.example.presidentvote;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {
    Handler handler = new Handler();
    Runnable r = new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent(getApplicationContext(),Login.class);
            startActivity(intent);
            finish();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         ActionBar actionBar = getSupportActionBar();
         actionBar.hide();
    }

    protected void onResume() {
        super.onResume();
        handler.postDelayed(r, 3000);
    }

    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(r);
    }
}
