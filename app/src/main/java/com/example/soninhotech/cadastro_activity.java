package com.example.soninhotech;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class cadastro_activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_activity);

        ImageButton btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(v -> finish());

        Button btnRegister = findViewById(R.id.cadastro_botao);
        btnRegister.setOnClickListener(v -> {
            // TODO: validar dados, inicializacao
            Intent intent = new Intent(
                    cadastro_activity.this,
                    tela_principal_activity.class
            );
            startActivity(intent);
        });
    }
}
