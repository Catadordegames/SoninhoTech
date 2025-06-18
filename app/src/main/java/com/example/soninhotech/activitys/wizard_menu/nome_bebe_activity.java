package com.example.soninhotech.activitys.wizard_menu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.soninhotech.R;

public class nome_bebe_activity extends AppCompatActivity {

    private EditText nomeEditText;
    private Button proximoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nome_bebe_activity);

        nomeEditText = findViewById(R.id.campoNomeBebe);
        proximoButton = findViewById(R.id.button);

        proximoButton.setOnClickListener(v -> {
            String nome = nomeEditText.getText().toString().trim();
            if(!nome.isEmpty()) {

                SharedPreferences prefs = getSharedPreferences("APP_PREFS", MODE_PRIVATE);
                prefs.edit().putString("NOME_BEBE", nome).apply();

                Intent intent = new Intent(this, data_nasc_bebe_activity.class);
                startActivity(intent);
            }else
                nomeEditText.setError("insira um nome");
        });
    }
}
