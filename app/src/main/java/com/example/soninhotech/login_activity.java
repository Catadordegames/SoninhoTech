package com.example.soninhotech;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class login_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        Button btnLogin = findViewById(R.id.login_botao);
        btnLogin.setOnClickListener(v -> {
            // TODO: authenticate login
            Intent intent = new Intent(
                    login_activity.this,
                    tela_principal_activity.class
            );
            startActivity(intent);
        });

        Button btnCadastro = findViewById(R.id.cadastro_botao);
        btnCadastro.setOnClickListener(v -> {
            Intent intent = new Intent(
                    login_activity.this,
                    cadastro_activity.class
            );
            startActivity(intent);
        });

        // TODO: esqueci senha
    }
}