package com.example.soninhotech.activitys.wizard_menu;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.soninhotech.R;
import com.example.soninhotech.activitys.selecionar_perfil_activity;
import com.example.soninhotech.data.AppDatabase;
import com.example.soninhotech.data.entity.Bebe;
import com.example.soninhotech.repository.MeuApp;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class foto_perfil_bebe_activity extends AppCompatActivity {

    private ShapeableImageView profileImageView;
    private Button chooseImageButton;
    private Button nextButton;

    private Uri imagemSelecionadaUri = null;

    // Launcher moderno para solicitar permissões
    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    abrirGaleria();
                } else {
                    Toast.makeText(this, "Permissão para acessar a galeria é necessária.", Toast.LENGTH_LONG).show();
                }
            });

    // Launcher moderno para pegar uma imagem da galeria
    private final ActivityResultLauncher<Intent> pickImageLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    imagemSelecionadaUri = result.getData().getData();
                    profileImageView.setImageURI(imagemSelecionadaUri);
                    nextButton.setEnabled(true); // Habilita o botão "Próximo"
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.foto_perfil_bebe_activity);

        // Inicializa os componentes da UI
        profileImageView = findViewById(R.id.profileImageView);
        chooseImageButton = findViewById(R.id.chooseImageButton);
        nextButton = findViewById(R.id.nextButton);

        Intent previwsActivity = getIntent();
        String nome = previwsActivity.getStringExtra("nome");
        String nascimento = previwsActivity.getStringExtra("nascimento");
        int sexo = previwsActivity.getIntExtra("sexo", 1);
        SharedPreferences prefs = getSharedPreferences("APP_PREFS", MODE_PRIVATE);
        String idUsuario = prefs.getString("ID_USUARIO_LOGADO", null);

        // O botão "Próximo" começa desabilitado
        nextButton.setEnabled(false);

        // Define a ação do botão para escolher a imagem
        chooseImageButton.setOnClickListener(v -> verificarPermissaoEabrirGaleria());

        // Define a ação do botão "Próximo"
        nextButton.setOnClickListener(v -> {
            if (imagemSelecionadaUri != null) {
                // Crie a instância do Executor
                Executor executor = Executors.newSingleThreadExecutor();

                // Inicie a tarefa de salvar em segundo plano
                executor.execute(() -> {
                    // --- CÓDIGO EM SEGUNDO PLANO ---
                    try {
                        if(idUsuario == null || idUsuario.isEmpty()) {
                            Log.e("idUsuario", "nenhum valor recebido");
                            return;
                        }
                        Bebe bebeInstancia = new Bebe(idUsuario, nome, sexo, nascimento, imagemSelecionadaUri.toString());

                        // Pega a instância do banco e insere os dados
                        AppDatabase db = MeuApp.getDatabase(getApplicationContext());
                        db.bebeDao().insert(bebeInstancia);

                        // SUCESSO! A inserção funcionou. Agora podemos mudar de tela.
                        // Para isso, voltamos para a thread principal.
                        runOnUiThread(() -> {
                            // --- DE VOLTA À THREAD PRINCIPAL ---
                            Toast.makeText(foto_perfil_bebe_activity.this, "Perfil do bebê salvo!", Toast.LENGTH_SHORT).show();

                            // A NAVEGAÇÃO ACONTECE AQUI, APENAS DEPOIS DO SUCESSO!
                            Intent nextActivity = new Intent(foto_perfil_bebe_activity.this, selecionar_perfil_activity.class);
                            startActivity(nextActivity);

                            // Opcional: finalize a activity atual se não quiser que o usuário volte para ela
                            // finish();
                        });

                    } catch (Exception e) {
                        // ERRO! A inserção no banco falhou.
                        Log.e("DEBUG_BEBE", "Erro ao salvar o perfil do bebê no banco.", e);

                        // Notifique o usuário sobre o erro, também na thread principal
                        runOnUiThread(() -> {
                            Toast.makeText(foto_perfil_bebe_activity.this, "Não foi possível salvar o perfil.", Toast.LENGTH_LONG).show();
                        });
                    }
                });
            } else {
                Toast.makeText(this, "Nenhuma imagem selecionada.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void verificarPermissaoEabrirGaleria() {
        String permissao;
        // Verifica a versão do Android para pedir a permissão correta
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            permissao = Manifest.permission.READ_MEDIA_IMAGES;
        } else {
            permissao = Manifest.permission.READ_EXTERNAL_STORAGE;
        }

        if (ContextCompat.checkSelfPermission(this, permissao) == PackageManager.PERMISSION_GRANTED) {
            // Se a permissão já foi concedida, abre a galeria
            abrirGaleria();
        } else {
            // Se não, solicita a permissão
            requestPermissionLauncher.launch(permissao);
        }
    }

    private void abrirGaleria() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickImageLauncher.launch(intent);
    }
}