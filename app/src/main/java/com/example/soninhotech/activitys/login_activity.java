package com.example.soninhotech.activitys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.soninhotech.R;
import com.example.soninhotech.data.AppDatabase;
import com.example.soninhotech.data.entity.Usuario;
import com.example.soninhotech.repository.MeuApp;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class login_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        Button btnLogin = findViewById(R.id.login_botao);
        Button btn_esqueci_senha = findViewById(R.id.esqueci_senha_botao);
        Button btnCadastro = findViewById(R.id.cadastro_botao);
        EditText nome = findViewById(R.id.login_usuario);
        EditText senha = findViewById(R.id.login_senha);

        btnLogin.setOnClickListener(v -> {
            // Pega o texto dos EditTexts ANTES de ir para a thread de fundo
            final String nomeInput = nome.getText().toString().trim();
            final String senhaInput = senha.getText().toString();

            // Validação básica para não fazer consulta desnecessária ao banco
            if (nomeInput.isEmpty() || senhaInput.isEmpty()) {
                Toast.makeText(login_activity.this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show();
                return; // Para a execução aqui
            }

            // 1. Executor para rodar a operação de banco em uma thread de fundo
            Executor executor = Executors.newSingleThreadExecutor();
            executor.execute(() -> {
                // --- ESTE CÓDIGO RODA EM SEGUNDO PLANO ---

                // Pega a instância do banco
                AppDatabase db = MeuApp.getDatabase(getApplicationContext());

                // Chama a autenticação e GUARDA o resultado
                Usuario usuarioAutenticado = db.usuarioDao().autenticar(nomeInput, senhaInput);

                // --- VOLTA PARA A THREAD PRINCIPAL PARA ATUALIZAR A UI ---
                runOnUiThread(() -> {
                    // 2. Verifica se o resultado NÃO é nulo (ou seja, login com sucesso)
                    if (usuarioAutenticado != null) {
                        // SUCESSO!
                        Toast.makeText(login_activity.this, "Login bem-sucedido!", Toast.LENGTH_SHORT).show();

                        // 3. Navega para a próxima tela SOMENTE em caso de sucesso
                        SharedPreferences prefs = getSharedPreferences("APP_PREFS", MODE_PRIVATE);
                        SharedPreferences.Editor editor= prefs.edit();
                        editor.putString("ID_USUARIO_LOGADO", usuarioAutenticado.id);
                        editor.apply();
                        String idUsuarioLogado = prefs.getString("ID_USUARIO_LOGADO", null);
                        Log.e("id usuario logado",idUsuarioLogado );
                        Intent intent = new Intent(login_activity.this, selecionar_perfil_activity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(login_activity.this, "Usuário ou senha inválidos.", Toast.LENGTH_LONG).show();
                    }
                });
            });
        });

        btnCadastro.setOnClickListener(v -> {
            Intent intent = new Intent(
                    login_activity.this,
                    cadastro_activity.class
            );
            startActivity(intent);
        });

        btn_esqueci_senha.setOnClickListener(v -> {
            Intent intent = new Intent(
                    login_activity.this,
                    esqueci_senha_activity.class
            );
            startActivity(intent);
        });

        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            Log.d("DB_DEBUG", "Forçando a abertura do banco de dados...");
            try {
                // Apenas pegar a instância força a criação e abertura
                AppDatabase db = MeuApp.getDatabase(getApplicationContext());
                // Uma operação simples para garantir que está funcionando
                db.query("SELECT 1", null);
                Log.d("DB_DEBUG", "Banco de dados aberto com sucesso!");
            } catch (Exception e) {
                Log.e("DB_DEBUG", "Erro ao abrir o banco de dados!", e);
            }
        });
    }
}