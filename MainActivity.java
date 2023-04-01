package com.example.scorecount;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    public static final String EXTRA_MESSAGE = "com.example.scorecounterapp.EXTRA_MESSAGE";
    public static final String EXTRA_HOME= "com.example.scorecounterapp.EXTRA_HOME";
    public static final String EXTRA_AWAY = "com.example.scorecounterapp.EXTRA_AWAY";

    private int homeCounter = 0;
    private int awayCounter = 0;
    private String victory;

    private TextView home_view;
    private TextView away_view;
    private Button button_team_one;
    private Button button_team_two;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        home_view = findViewById(R.id.teamOne_scoreDisplay);
        away_view = findViewById(R.id.teamTwo_scoreDisplay);
        button_team_one = findViewById(R.id.button_teamone);
        button_team_two = findViewById(R.id.button_teamtwo);
        home_view.setText("home " + homeCounter);
        away_view.setText("away " + awayCounter);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("home ", homeCounter);
        outState.putInt("away ", awayCounter);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        homeCounter = savedInstanceState.getInt("home ", homeCounter);
        awayCounter = savedInstanceState.getInt("away ", awayCounter);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        home_view.setText("home " + homeCounter);
        away_view.setText("away " + awayCounter);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void home_button(View view) {
        if(button_team_one.equals(view)){
            homeCounter += 1;
            home_view.setText("Score: " + String.valueOf(homeCounter));
            if(homeCounter == 5){
                victoryyy();
            }
        }
    }

    public void away_button(View view) {
        if(button_team_two.equals(view)){
            awayCounter += 1;
            away_view.setText("Score: " + String.valueOf(awayCounter) );
            if(awayCounter== 5){
                victoryyy();
            }
        }
    }



    public void victoryyy(){
        if(homeCounter == Math.max(homeCounter, awayCounter)){
            victory = "Home Team";
        }
        else{
            victory = "Away Team";
        }

        Intent intent = new Intent(this, SecondActivityy.class);
        intent.putExtra(EXTRA_MESSAGE, victory);
        intent.putExtra(EXTRA_HOME, homeCounter);
        intent.putExtra(EXTRA_AWAY, awayCounter);
        startActivity(intent);
    }



}