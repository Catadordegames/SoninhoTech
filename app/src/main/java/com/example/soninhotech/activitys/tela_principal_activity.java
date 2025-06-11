package com.example.soninhotech.activitys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.soninhotech.R;
import com.example.soninhotech.activitys.wizard_menu.foto_perfil_bebe_activity;
import com.example.soninhotech.data.AppDatabase;
import com.example.soninhotech.data.entity.Bebe;
import com.example.soninhotech.repository.MeuApp;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class tela_principal_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_principal_activity);

        SharedPreferences prefs = getSharedPreferences("APP_PREFS", MODE_PRIVATE);
        String idUsuario = prefs.getString("ID_USUARIO_LOGADO", null);
        int idBebeLogado = prefs.getInt("ID_BEBE_LOGADO", 0);
        TextView age = findViewById(R.id.age);
        ImageButton foto = findViewById(R.id.avatar);
        Button btnLogout = findViewById(R.id.btn_logout);
        ImageButton btnCadSono = findViewById(R.id.btn_cadastro_sono);
        ImageButton btnCadAlim = findViewById(R.id.btn_cadastro_alim);
        Button btnRelSono = findViewById(R.id.btn_relatorio_sono);
        Button btnRelAlimentacao = findViewById(R.id.btn_relatorio_alim);

        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            // --- CÓDIGO EM SEGUNDO PLANO ---
            if (idBebeLogado == 0){
                Log.e("id bebe logado", Integer.toString(idBebeLogado));
                return;
            }
            AppDatabase db = MeuApp.getDatabase(getApplicationContext());
            Bebe bebe = db.bebeDao().getById(idBebeLogado);
            runOnUiThread(() -> {
                // codigo da UI
                foto.setImageURI(Uri.parse(bebe.foto));
            });
        });


        btnLogout.setOnClickListener(v -> {
            Intent intent = new Intent(
                tela_principal_activity.this,
                login_activity.class
            );
            startActivity(intent);
        });


        btnCadSono.setOnClickListener(v -> {
            Intent intent = new Intent(
                tela_principal_activity.this,
                cadastro_sono_activity.class
            );
            startActivity(intent);
        });


        btnCadAlim.setOnClickListener(v -> {
            Intent intent = new Intent(
                tela_principal_activity.this,
                cadastro_alim_activity.class
            );
            startActivity(intent);
        });


        btnRelSono.setOnClickListener(v -> {
            Intent intent = new Intent(
                    tela_principal_activity.this,
                    relatorio_sono_activity.class
            );
            startActivity(intent);
        });


        btnRelAlimentacao.setOnClickListener(v -> {
            Intent intent = new Intent(
                tela_principal_activity.this,
                relatorio_alimentacao_activity.class
            );
            startActivity(intent);
        });
    }
}