package com.example.soninhotech;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.widget.EditText;

public class Timer {
    long startTime = 0;
    long runTime = 0;
    int running = -1;
    public EditText outputView;

    //runs without a timer by reposting this handler at the end of the runnable
    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {
        @SuppressLint("DefaultLocale")
        @Override
        public void run() {
            long millis = System.currentTimeMillis() - startTime + runTime;
            int seconds = (int) (millis / 1000);
            int minutes = seconds / 60;
            seconds = seconds % 60;

            if (outputView != null) outputView.setText(String.format("%d:%02d", minutes, seconds));

            timerHandler.postDelayed(this, 500);
        }
    };


    public void reset() {
        pause();
        if (outputView != null) outputView.setText(R.string.time_default);
        startTime = runTime = 0;
        running = -1;
    }

    public void pause() {
        if (running == 1) {
            timerHandler.removeCallbacks(timerRunnable);
            long curTime = System.currentTimeMillis();
            runTime += curTime - startTime;
        }
        running = 0;
    }

    public void play() {
        startTime = System.currentTimeMillis();
        timerHandler.postDelayed(timerRunnable, 0);
        running = 1;
   }
}
