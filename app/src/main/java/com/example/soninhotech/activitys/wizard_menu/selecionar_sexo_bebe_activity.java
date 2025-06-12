package com.example.soninhotech.activitys.wizard_menu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.soninhotech.R;

public class selecionar_sexo_bebe_activity extends AppCompatActivity {

    private ImageButton botaoMasculino, botaoFeminino;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selecionar_sexo_bebe_activity);

        botaoMasculino = findViewById(R.id.botaoMasculino);
        botaoFeminino = findViewById(R.id.botaoFeminino);

        botaoMasculino.setOnClickListener(v -> salvarSexoEir(1));
        botaoFeminino.setOnClickListener(v -> salvarSexoEir(2));
    }

    private void salvarSexoEir(int sexo) {
        SharedPreferences prefs = getSharedPreferences("APP_PREFS", MODE_PRIVATE);
        String nome = prefs.getString("NOME_BEBE", "");
        String nascimento = prefs.getString("NASCIMENTO_BEBE", "");

        Intent intent = new Intent(this, foto_perfil_bebe_activity.class);
        intent.putExtra("nome", nome);
        intent.putExtra("nascimento", nascimento);
        intent.putExtra("sexo", sexo);
        startActivity(intent);
    }
}
