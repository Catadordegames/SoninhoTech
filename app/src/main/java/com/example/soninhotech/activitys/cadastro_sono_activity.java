package com.example.soninhotech.activitys;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.soninhotech.R;
import com.google.android.material.snackbar.Snackbar;

public class cadastro_sono_activity extends AppCompatActivity {

    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_sono_activity);

        ImageButton btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(v -> finish());

        ImageButton btnStartPause = findViewById(R.id.btn_start_pause);
        ImageButton btnStop = findViewById(R.id.btn_stop);
        timer = new Timer();
        timer.outputView = findViewById(R.id.input_time);

        btnStartPause.setOnClickListener(v -> {
            if (timer.running == 1) {
                timer.pause();
                btnStartPause.setImageResource(android.R.drawable.ic_media_play);
                btnStartPause.setContentDescription(getString(R.string.start));
            } else {
                timer.play();
                btnStartPause.setImageResource(android.R.drawable.ic_media_pause);
                btnStartPause.setContentDescription(getString(R.string.pause));
            }
        });

        btnStop.setOnClickListener(v -> {
            timer.reset();
            btnStartPause.setImageResource(android.R.drawable.ic_media_play);
            btnStartPause.setContentDescription(getString(R.string.start));
        });

        Button btnSave = findViewById(R.id.btn_save);
        btnSave.setOnClickListener(v -> {
            timer.pause();
            btnStartPause.setImageResource(android.R.drawable.ic_media_play);
            btnStartPause.setContentDescription(getString(R.string.start));

            btnStartPause.setEnabled(false);
            btnStop.setEnabled(false);
            timer.outputView.setEnabled(false);

            Handler handler = new Handler();
            handler.postDelayed(() -> {
                Snackbar popupMsg = Snackbar.make(v, R.string.report_saved, 4000);
                popupMsg.show();
                handler.postDelayed(this::finish, 4000);
            }, 500);
        });
    }
}