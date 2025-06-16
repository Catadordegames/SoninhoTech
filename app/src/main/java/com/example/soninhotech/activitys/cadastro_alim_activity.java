package com.example.soninhotech.activitys;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.soninhotech.R;
import com.example.soninhotech.data.AppDatabase;
import com.example.soninhotech.data.entity.RegistroAlimentacao;
import com.example.soninhotech.repository.MeuApp;
import com.example.soninhotech.repository.StaticFunctions;
import com.google.android.material.snackbar.Snackbar;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class cadastro_alim_activity extends AppCompatActivity {

    private EditText campoInicio, campoFim;
    private Button botaoSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_alim_activity);

        campoInicio = findViewById(R.id.campoInicio);
        campoFim = findViewById(R.id.campoFim);
        ImageButton botaoVoltar = findViewById(R.id.btn_back);
        botaoSalvar = findViewById(R.id.botaoSalvar);
        SharedPreferences prefs = getSharedPreferences("APP_PREFS", MODE_PRIVATE);
        int bebe = prefs.getInt("ID_BEBE_LOGADO", 0);

        campoInicio.setOnClickListener(v -> escolherDataHora(campoInicio));
        campoFim.setOnClickListener(v -> escolherDataHora(campoFim));

        botaoVoltar.setOnClickListener(v -> finish());

        botaoSalvar.setOnClickListener(v -> {
            String inicio = campoInicio.getText().toString().trim();
            String fim = campoFim.getText().toString().trim();

            if(inicio.isEmpty()) {
                campoInicio.setError("Preencha a data e hora de início");
                return;
            }
            if(fim.isEmpty()) {
                fim = null;
            }

            final String inicioFormatado = StaticFunctions.formatarDataHoraBd(inicio);
            final String fimFormatado = StaticFunctions.formatarDataHoraBd(fim);

            Executor executor = Executors.newSingleThreadExecutor();
            executor.execute(() -> {
                // --- CÓDIGO EM SEGUNDO PLANO ---
                //constroi o objeto
                RegistroAlimentacao alimentacao = new RegistroAlimentacao(bebe,inicioFormatado, fimFormatado);
                AppDatabase db = MeuApp.getDatabase(getApplicationContext());
                db.registroAlimentacaoDao().insert(alimentacao);
                runOnUiThread(() -> {
                    Toast.makeText(this, "Alimentação salva com sucesso!", Toast.LENGTH_SHORT).show();
                    finish();
                });
            });
        });
    }

    private void escolherDataHora(EditText campo) {
        final Calendar calendario = Calendar.getInstance();

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, ano, mes, dia) -> {
                    // Após escolher a data, abre o TimePicker
                    TimePickerDialog timePickerDialog = new TimePickerDialog(
                            cadastro_alim_activity.this,
                            (horaView, hora, minuto) -> {
                                String dataHoraFormatada = String.format("%02d/%02d/%04d %02d:%02d",
                                        dia, mes + 1, ano, hora, minuto);
                                campo.setText(dataHoraFormatada);
                            },
                            calendario.get(Calendar.HOUR_OF_DAY),
                            calendario.get(Calendar.MINUTE),
                            true
                    );
                    timePickerDialog.show();
                },
                calendario.get(Calendar.YEAR),
                calendario.get(Calendar.MONTH),
                calendario.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }
}