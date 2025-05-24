package com.example.soninhotech;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class cadastro_sono_activity extends AppCompatActivity {

    EditText timerInput;
    long startTime = 0;

    //runs without a timer by reposting this handler at the end of the runnable
    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {
        @SuppressLint("DefaultLocale")
        @Override
        public void run() {
            long millis = System.currentTimeMillis() - startTime;
            int seconds = (int) (millis / 1000);
            int minutes = seconds / 60;
            seconds = seconds % 60;

            timerInput.setText(String.format("%d:%02d", minutes, seconds));

            timerHandler.postDelayed(this, 500);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_sono_activity);

        ImageButton btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(v -> finish());

        ImageButton btnStart = findViewById(R.id.btn_start);
        ImageButton btnStop = findViewById(R.id.btn_stop);
        timerInput = findViewById(R.id.input_time);

        btnStart.setOnClickListener(v -> {
            startTime = System.currentTimeMillis();
            timerHandler.postDelayed(timerRunnable, 0);
        });

        btnStop.setOnClickListener(v -> timerHandler.removeCallbacks(timerRunnable));


        Button btnSave = findViewById(R.id.btn_save);
        btnSave.setOnClickListener(v -> {
            Handler handler = new Handler();
            handler.postDelayed(() -> {
                Snackbar popupMsg = Snackbar.make(v, R.string.report_saved, 4000);
                popupMsg.show();
                handler.postDelayed(this::finish, 4000);
            }, 500);
        });
    }
}