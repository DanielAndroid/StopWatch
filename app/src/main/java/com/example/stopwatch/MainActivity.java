package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Chronometer chronometer;
    Button startAndStop, reset;
    Boolean running = false;
    long pauseOffSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chronometer = findViewById(R.id.chronometer);
        startAndStop = findViewById(R.id.start_stop);
        startAndStop.setOnClickListener(this);
        reset = findViewById(R.id.reset);
        reset.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.start_stop:
                if(!running) {
                    chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffSet);
                    chronometer.start();
                    startAndStop.setText(R.string.stop);
                    startAndStop.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    running = true;
                } else {
                    chronometer.stop();
                    pauseOffSet = SystemClock.elapsedRealtime() - chronometer.getBase();
                    startAndStop.setText(R.string.start);
                    startAndStop.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    running = false;
                }
                break;

            case R.id.reset:
                chronometer.setBase(SystemClock.elapsedRealtime());
                pauseOffSet = 0;
                chronometer.stop();
                startAndStop.setText(R.string.start);
                startAndStop.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                running = false;
                break;
        }
    }
}