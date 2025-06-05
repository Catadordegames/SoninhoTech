package com.example.soninhotech;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class selecionar_perfil_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecionar_perfil);

        conexaoDB = new ConexaoDB(this);
        bebe = new Bebe(conexaoDB);
        View layoutContainer = findViewById(R.id.LinearLayout2);

        mostrarBebes();
    }

    private void mostrarBebes(){
        List<String> nomes = bebe.getAllUserNames();

        if (nomes != null) { // Good practice to check for null
            for (String nome : nomes) {
                TextView textView = new TextView(this);
                textView.setText(nome);
                textView.setTextSize(18f); // f for float
                textView.setPadding(16, 16, 16, 16);
            }
            layoutContainer.addView(textView);
        }

    }
}