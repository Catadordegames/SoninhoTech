package com.example.soninhotech.activitys;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.soninhotech.R;

public class relatorio_sono_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.relatorio_sono_activity);

        ImageButton btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(v -> {
            finish();
        });
    }
}