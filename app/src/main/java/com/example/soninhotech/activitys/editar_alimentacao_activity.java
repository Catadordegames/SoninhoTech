package com.example.soninhotech.activitys;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.soninhotech.R;
import com.example.soninhotech.data.entity.RegistroAlimentacao;
import com.example.soninhotech.repository.MeuApp;
import com.example.soninhotech.repository.StaticFunctions;

import java.util.Calendar;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class editar_alimentacao_activity extends AppCompatActivity {

    private EditText editInicio, editFim;
    private Button btnSalvar;
    private ImageButton btnVoltar;
    private RegistroAlimentacao registro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editar_alimentacao_activity);

        editInicio = findViewById(R.id.campoInicio);
        editFim = findViewById(R.id.campoFim);
        btnSalvar = findViewById(R.id.btnSalvarAlteracoes);
        btnVoltar = findViewById(R.id.btn_back);

        // Recupera objeto
        registro = (RegistroAlimentacao) getIntent().getSerializableExtra("registro");

        if (registro != null) {
            editInicio.setText(StaticFunctions.formatarDataHoraBr(registro.inicio));
            if (registro.fim != null) {
                editFim.setText(StaticFunctions.formatarDataHoraBr(registro.fim));
            }
        }

        editInicio.setOnClickListener(v -> abrirDateTimePicker(editInicio));
        editFim.setOnClickListener(v -> abrirDateTimePicker(editFim));

        btnSalvar.setOnClickListener(v -> {
            String inicioBr = editInicio.getText().toString().trim();
            String fimBr = editFim.getText().toString().trim();

            if (inicioBr.isEmpty()) {
                editInicio.setError("Campo obrigatório");
                return;
            }

            // Converte para formato ISO
            registro.inicio = StaticFunctions.formatarDataHoraBd(inicioBr);
            registro.fim = fimBr.isEmpty() ? null : StaticFunctions.formatarDataHoraBd(fimBr);

            Executor executor = Executors.newSingleThreadExecutor();
            executor.execute(() -> {
                MeuApp.getDatabase(getApplicationContext())
                        .registroAlimentacaoDao()
                        .update(registro);

                runOnUiThread(this::finish); // Volta para tela anterior
            });
        });

        btnVoltar.setOnClickListener(v -> finish());
    }

    private void abrirDateTimePicker(EditText campo) {
        final Calendar c = Calendar.getInstance();

        int ano = c.get(Calendar.YEAR);
        int mes = c.get(Calendar.MONTH);
        int dia = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePicker = new DatePickerDialog(this, (view, y, m, d) -> {
            TimePickerDialog timePicker = new TimePickerDialog(this, (tpView, hour, minute) -> {
                String dataHoraBr = String.format("%02d/%02d/%04d %02d:%02d", d, m + 1, y, hour, minute);
                campo.setText(dataHoraBr);
            }, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true);

            timePicker.show();
        }, ano, mes, dia);

        datePicker.show();
    }
}