package com.example.soninhotech;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class tela_principal_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_principal_activity);

        ImageButton btnRelSono = findViewById(R.id.imageButton_sono);
        btnRelSono.setOnClickListener(v -> {
            Intent intent = new Intent(
                tela_principal_activity.this,
                relatorio_sono_activity.class
            );
            startActivity(intent);
        });

       ImageButton btnRelAlimentacao = findViewById(R.id.imageButton_alim);
        btnRelAlimentacao.setOnClickListener(v -> {
            Intent intent = new Intent(
                tela_principal_activity.this,
                relatorio_alimentacao_activity.class
            );
            startActivity(intent);
        });
    }
}