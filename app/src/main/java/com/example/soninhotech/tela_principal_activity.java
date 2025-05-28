package com.example.soninhotech;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class tela_principal_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_principal_activity);

        Button btnLogout = findViewById(R.id.btn_logout);
        btnLogout.setOnClickListener(v -> {
            Intent intent = new Intent(
                tela_principal_activity.this,
                login_activity.class
            );
            startActivity(intent);
        });

        ImageButton btnCadSono = findViewById(R.id.btn_cadastro_sono);
        btnCadSono.setOnClickListener(v -> {
            Intent intent = new Intent(
                tela_principal_activity.this,
                cadastro_sono_activity.class
            );
            startActivity(intent);
        });

        ImageButton btnCadAlim = findViewById(R.id.btn_cadastro_alim);
        btnCadAlim.setOnClickListener(v -> {
            Intent intent = new Intent(
                tela_principal_activity.this,
                cadastro_alim_activity.class
            );
            startActivity(intent);
        });

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