package com.example.soninhotech.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.soninhotech.R;
import com.example.soninhotech.data.AppDatabase;
import com.example.soninhotech.data.entity.Usuario;
import com.example.soninhotech.repository.MeuApp;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class cadastro_activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_activity);


        EditText nome = findViewById(R.id.cadastro_usuario_nome);
        EditText email = findViewById(R.id.cadastro_usuario_email);
        EditText senha = findViewById(R.id.cadastro_usuario_senha);
        ImageButton btnBack = findViewById(R.id.btn_back);
        Button btnRegister = findViewById(R.id.cadastro_botao);

        btnBack.setOnClickListener(v -> finish());
        btnRegister.setOnClickListener(v -> {
            Log.e("","botão de cadastro apertado apertado ");
            Executor executor = Executors.newSingleThreadExecutor();
            executor.execute(() -> {
                Usuario usuarioinstancia = new Usuario(nome.getText().toString(), email.getText().toString(), senha.getText().toString());
                AppDatabase db = MeuApp.getDatabase(getApplicationContext());
                db.usuarioDao().insertUsuario(usuarioinstancia);
            });
            Toast.makeText(getApplicationContext(), "cadastro concluido", Toast.LENGTH_SHORT).show();
            Intent nextActivity = new Intent(cadastro_activity.this, login_activity.class);
            startActivity(nextActivity);
        });
    }
}
