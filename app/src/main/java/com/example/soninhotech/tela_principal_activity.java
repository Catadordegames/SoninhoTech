package com.example.soninhotech;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class tela_principal_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_principal_activity);

        Button btnLogout = findViewById(R.id.btn_logout);
        btnLogout.setOnClickListener(v -> finish());

        Button btnRelSono = findViewById(R.id.btn_relatorio_sono);
        btnRelSono.setOnClickListener(v -> {
            Intent intent = new Intent(
                    tela_principal_activity.this,
                    relatorio_sono_activity.class
            );
            startActivity(intent);
        });

        Button btnRelAlimentacao = findViewById(R.id.btn_relatorio_alim);
        btnRelAlimentacao.setOnClickListener(v -> {
            Intent intent = new Intent(
                tela_principal_activity.this,
                relatorio_alimentacao_activity.class
            );
            startActivity(intent);
        });
    }
}