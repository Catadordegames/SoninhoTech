package com.example.soninhotech;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class esqueci_senha_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.esqueci_senha_activity);

        ImageButton btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(v -> finish());

        Button btnRegister = findViewById(R.id.botao_enviar);
        btnRegister.setOnClickListener(v -> {
            ProgressBar progressBar = findViewById(R.id.progressBar);
            progressBar.setVisibility(View.VISIBLE);

            // TODO: recuperação de senha
            Handler handler = new Handler();
            handler.postDelayed(() -> {
                Snackbar popupMsg = Snackbar.make(v, R.string.email_sent, 4000);
                popupMsg.show();
                handler.postDelayed(this::finish, 4000);
            }, 2000);
        });
    }
}