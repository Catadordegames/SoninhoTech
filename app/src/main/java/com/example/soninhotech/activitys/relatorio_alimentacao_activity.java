package com.example.soninhotech.activitys;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.soninhotech.R;

public class relatorio_alimentacao_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.relatorio_alimentacao_activity);

        ImageButton btnBack = findViewById(R.id.btn_back);
        SharedPreferences prefs = getSharedPreferences("APP_PREFS", MODE_PRIVATE);
        String idUsuario = prefs.getString("ID_USUARIO_LOGADO", null);

        btnBack.setOnClickListener(v -> {
            finish();
        });
    }
}