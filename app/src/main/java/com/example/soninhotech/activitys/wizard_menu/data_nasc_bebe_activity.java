package com.example.soninhotech.activitys.wizard_menu;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.soninhotech.R;

import java.util.Calendar;

public class data_nasc_bebe_activity extends AppCompatActivity {

    private EditText campoData;
    private Button botaoProximo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_nasc_bebe_activity);

        campoData = findViewById(R.id.campoData);
        botaoProximo = findViewById(R.id.button);

        campoData.setOnClickListener(v -> abrirDatePicker());

        botaoProximo.setOnClickListener(v -> {
            String data = campoData.getText().toString().trim();

            if (!data.isEmpty()) {
                SharedPreferences prefs = getSharedPreferences("APP_PREFS", MODE_PRIVATE);
                prefs.edit().putString("NASCIMENTO_BEBE", data).apply();

                Intent intent = new Intent(this, selecionar_sexo_bebe_activity.class);
                startActivity(intent);
            } else {
                campoData.setError("Escolha uma data");
            }
        });
    }

    private void abrirDatePicker() {
        Calendar c = Calendar.getInstance();
        int ano = c.get(Calendar.YEAR);
        int mes = c.get(Calendar.MONTH);
        int dia = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, year, month, dayOfMonth) -> {
                    String dataSelecionada = String.format("%02d/%02d/%04d", dayOfMonth, month + 1, year);
                    campoData.setText(dataSelecionada);
                },
                ano, mes, dia
        );
        datePickerDialog.show();
    }
}
