package com.example.soninhotech.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.soninhotech.R;

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

        Button btn_esqueci_senha = findViewById(R.id.esqueci_senha_botao);
        btn_esqueci_senha.setOnClickListener(v -> {
            Intent intent = new Intent(
                    login_activity.this,
                    esqueci_senha_activity.class
            );
            startActivity(intent);
        });
    }
}