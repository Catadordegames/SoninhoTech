package com.example.soninhotech.activitys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.soninhotech.R;
import com.example.soninhotech.data.entity.RegistroAlimentacao;
import com.example.soninhotech.data.entity.RegistroSono;
import com.example.soninhotech.repository.MeuApp;
import com.example.soninhotech.repository.StaticFunctions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class relatorio_sono_activity extends AppCompatActivity{

    private ListView listView;
    private List<RegistroSono> listaRegistros;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.relatorio_sono_activity);

        listView = findViewById(R.id.listaItens);
        ImageButton back = findViewById(R.id.btn_back);

        back.setOnClickListener(v -> finish());
        carregarDados();
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarDados();
    }


    private void carregarDados() {
        SharedPreferences prefs = getSharedPreferences("APP_PREFS", MODE_PRIVATE);
        int idBebeLogado = prefs.getInt("ID_BEBE_LOGADO", 0);
        // Carrega dados em background
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            listaRegistros = MeuApp.getDatabase(getApplicationContext())
                    .registroSonoDao().getAll(idBebeLogado);

            // Monta lista de strings só com o campo "inicio"
            List<String> listaInicio = new java.util.ArrayList<>();
            for (RegistroSono r : listaRegistros) {
                String inicioFormatado = StaticFunctions.formatarDataHoraBr(r.inicio);
                String fimFormatado = StaticFunctions.formatarDataHoraBr(r.fim);
                listaInicio.add(inicioFormatado + " (duração " + StaticFunctions.calcularDuracao(r.inicio, r.fim) + ")");
                Log.d("DEBUG", "Inicio: " + r.inicio + ", Fim: " + r.fim);

            }

            // Volta para thread principal para atualizar UI
            runOnUiThread(() -> {
                adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaInicio);
                listView.setAdapter(adapter);

                listView.setOnItemClickListener((parent, view, position, id) -> {
                    RegistroSono registroSelecionado = listaRegistros.get(position);

                    Intent intent = new Intent(relatorio_sono_activity.this, editar_sono_activity.class);
                    intent.putExtra("registro", registroSelecionado);
                    startActivity(intent);
                });
            });
        });
    }



}