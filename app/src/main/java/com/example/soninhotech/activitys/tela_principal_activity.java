package com.example.soninhotech.activitys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
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
import com.example.soninhotech.repository.StaticFunctions;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class tela_principal_activity extends AppCompatActivity {

    TextView age, data, nomeBebe, sexoBebe;
    ImageButton foto, btnCadSono, btnCadAlim;
    Button btnLogout, btnRelSono, btnRelAlimentacao;
    Bebe bebe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_principal_activity);

        SharedPreferences prefs = getSharedPreferences("APP_PREFS", MODE_PRIVATE);
        String idUsuario = prefs.getString("ID_USUARIO_LOGADO", null);
        int idBebeLogado = prefs.getInt("ID_BEBE_LOGADO", 0);
        bebe = (Bebe) getIntent().getSerializableExtra("bebe");
        age = findViewById(R.id.age_value);
        data = findViewById(R.id.date_value);
        nomeBebe = findViewById(R.id.nome_bebe);
        sexoBebe = findViewById(R.id.sexo_bebe);
        foto = findViewById(R.id.avatar);
        btnCadSono = findViewById(R.id.btn_cadastro_sono);
        btnCadAlim = findViewById(R.id.btn_cadastro_alim);
        btnLogout = findViewById(R.id.btn_logout);
        btnRelSono = findViewById(R.id.btn_relatorio_sono);
        btnRelAlimentacao = findViewById(R.id.btn_relatorio_alim);


        Calendar calendario = Calendar.getInstance();

        int ano = calendario.get(Calendar.YEAR);
        int mes = calendario.get(Calendar.MONTH) + 1; // Janeiro = 0, por isso +1
        int dia = calendario.get(Calendar.DAY_OF_MONTH);


        String dataHora = String.format("%02d/%02d/%04d", dia, mes, ano);
        age.setText(StaticFunctions.calcularIdadeBebe(bebe.dataNascimento));
        data.setText(dataHora);
        nomeBebe.setText(bebe.nome);
        if (bebe.idSexo == 1)
            sexoBebe.setText("masculino");
        else
            sexoBebe.setText("feminino");
        foto.setImageURI(Uri.parse(bebe.foto));


        foto.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, 1); // 1 é o código de requisição
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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            Uri imagemSelecionada = data.getData();
            if (imagemSelecionada != null) {
                bebe.foto = imagemSelecionada.toString();
                foto.setImageURI(Uri.parse(bebe.foto));
                Executor executor = Executors.newSingleThreadExecutor();
                executor.execute(() -> {
                    AppDatabase db = MeuApp.getDatabase(getApplicationContext());
                    db.bebeDao().update(bebe);
                });
            }
        }
    }
}