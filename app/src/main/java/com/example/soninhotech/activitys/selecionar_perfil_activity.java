package com.example.soninhotech.activitys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.soninhotech.R;
import com.example.soninhotech.activitys.wizard_menu.nome_bebe_activity;
import com.example.soninhotech.data.AppDatabase;
import com.example.soninhotech.data.entity.Bebe;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class selecionar_perfil_activity extends AppCompatActivity {

    private LinearLayout layoutContainer;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selecionar_perfil_activity);

        layoutContainer = findViewById(R.id.LinearLayout2);
        ImageButton criar = findViewById(R.id.adicionarPerfil);

        // Obter instância do banco de dados
        db = AppDatabase.getInstance(getApplicationContext());

        mostrarBebes();

        criar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("","botão adicionar clicado");
                Intent nextAcitivity = new Intent(getApplicationContext(), nome_bebe_activity.class);
                startActivity(nextAcitivity);
            }
        });
    }

    private void mostrarBebes() {
        // 1. Executor para rodar a consulta ao banco em uma thread de fundo
        Executor executor = Executors.newSingleThreadExecutor();

        executor.execute(() -> {
            // Este código roda em SEGUNDO PLANO, evitando travamentos
            SharedPreferences prefs = getSharedPreferences("APP_PREFS", MODE_PRIVATE);
            String idUsuario = prefs.getString("ID_USUARIO_LOGADO", null);
            final List<Bebe> bebeList = db.bebeDao().getAllByFK(idUsuario);

            // 2. Após buscar os dados, voltamos para a thread principal para atualizar a UI
            runOnUiThread(() -> {
                // Este código roda na UI THREAD, onde podemos mexer na interface

                if (bebeList != null && !bebeList.isEmpty()) {
                    for (Bebe bebe : bebeList) {
                        if (bebe.foto != null && !bebe.foto.isEmpty()) {

                            // 3. Troca de ImageView para ImageButton
                            ImageButton imageButton = new ImageButton(this);

                            // Converte a string da foto para Uri e define no botão
                            imageButton.setImageURI(Uri.parse(bebe.foto));

                            // Configurações de layout (tamanho, margens, etc.)
                            int tamanho = (int) getResources().getDimension(R.dimen.tamanho_foto_bebe);
                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(tamanho, tamanho);
                            params.setMargins(16, 16, 16, 16);
                            imageButton.setLayoutParams(params);
                            imageButton.setScaleType(ImageButton.ScaleType.CENTER_CROP);

                            // 4. Adiciona o OnClickListener para tornar o botão funcional
                            imageButton.setOnClickListener(v -> {
                                // TODO: Coloque aqui a ação desejada, como abrir uma tela de detalhes
                                SharedPreferences.Editor editor= prefs.edit();
                                editor.putInt("ID_BEBE_LOGADO", bebe.id);
                                editor.apply();
                                int idBebeLogado = prefs.getInt("ID_BEBE_LOGADO", 0);
                                Log.i("id usuario logado", Integer.toString(idBebeLogado));
                                Intent intent = new Intent(this, tela_principal_activity.class);
                                startActivity(intent);
                            });

                            // Adiciona o ImageButton criado ao seu layout container
                            if (layoutContainer != null) {
                                layoutContainer.addView(imageButton);
                            }
                        }
                    }
                } else {
                    Log.d("MostrarBebes", "Nenhum bebê encontrado no banco de dados.");
                }
            });
        });

    }
}
