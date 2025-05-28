package com.example.soninhotech;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.widget.EditText;

public class Timer {
    long startTime = 0;
    long runTime = 0;
    int running = -1;
    public EditText outputView;
    boolean viewEnabled;

    //runs without a timer by reposting this handler at the end of the runnable
    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {
        @SuppressLint("DefaultLocale")
        @Override
        public void run() {
            if (outputView != null) outputView.setText(asString());

            timerHandler.postDelayed(this, 500);
        }
    };


    public void reset() {
        pause();
        if (outputView != null) outputView.setText("");
        startTime = runTime = 0;
        running = -1;
    }

    public void pause() {
        if (running == 1) {
            timerHandler.removeCallbacks(timerRunnable);
            long curTime = System.currentTimeMillis();
            runTime += curTime - startTime;
            outputView.setEnabled(viewEnabled);
        }
        running = 0;
    }

    public void play() {
        startTime = System.currentTimeMillis();
        timerHandler.postDelayed(timerRunnable, 0);
        viewEnabled = outputView.isEnabled();
        outputView.setEnabled(false);
        running = 1;
   }

   public long value() {
        if (running == -1) return -1;
        else if (running == 0) return runTime;
        else return runTime + System.currentTimeMillis() - startTime;
   }

    @SuppressLint("DefaultLocale")
    public String asString() {
        if (running == -1) return "";
        long millis = value();
        int seconds = (int) (millis / 1000);
        int minutes = seconds / 60;
        seconds = seconds % 60;
        return String.format("%d:%02d", minutes, seconds);
    }
}
