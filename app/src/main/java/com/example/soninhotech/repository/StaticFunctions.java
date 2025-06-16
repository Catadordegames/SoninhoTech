package com.example.soninhotech.repository;

import android.icu.util.Calendar;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class StaticFunctions {

    public static String formatarDataBd(String dataBr) {
        try {
            SimpleDateFormat formatoBrasil = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat formatoBd = new SimpleDateFormat("yyyy-MM-dd");
            Date dataOriginal = formatoBrasil.parse(dataBr);
            return formatoBd.format(dataOriginal);
        } catch (Exception e) {
            e.printStackTrace();
            return dataBr;
        }
    }

    public static String formatarDataBr(String dataDb) {
        try {
            SimpleDateFormat formatoDb = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat formatoBrasil = new SimpleDateFormat("dd/MM/yyyy");
            Date data = formatoDb.parse(dataDb);
            return formatoBrasil.format(data);
        } catch (Exception e) {
            e.printStackTrace();
            return dataDb;
        }
    }

    public static String formatarDataHoraBd(String dataHoraBr) {
        try {
            SimpleDateFormat formatoBrasil = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            SimpleDateFormat formatoInternacional = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date data = formatoBrasil.parse(dataHoraBr);
            return formatoInternacional.format(data);
        } catch (Exception e) {
            e.printStackTrace();
            return dataHoraBr; // retorna a string original caso dê erro
        }
    }

    public static String formatarDataHoraBr(String dataHoraInt) {
        try {
            SimpleDateFormat formatoInternacional = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            SimpleDateFormat formatoBrasil = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            Date data = formatoInternacional.parse(dataHoraInt);
            return formatoBrasil.format(data);
        } catch (Exception e) {
            e.printStackTrace();
            return dataHoraInt; // retorna a string original caso dê erro
        }
    }

    public static String calcularDuracao(String inicio, String fim) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
            sdf.setLenient(false); // Evita parse de datas malformadas

            Date dataInicio = sdf.parse(inicio);
            Date dataFim = sdf.parse(fim);

            long diferencaMillis = dataFim.getTime() - dataInicio.getTime();

            if (diferencaMillis < 0) {
                Log.d("tempo decorrido", "negativo");
                return "00:00";
            }

            long totalMinutos = diferencaMillis / (1000 * 60);
            long horas = totalMinutos / 60;
            long minutos = totalMinutos % 60;

            return String.format(Locale.getDefault(), "%02d:%02d", horas, minutos);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("tempo decorrido", "não foi possivel calcular: " + e.getMessage());
            return "00:00";
        }
    }

    public static String calcularIdadeBebe(String dataNascimentoDb) {
        try {
            // Formato padrão do banco de dados
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            Date dataNasc = sdf.parse(dataNascimentoDb);

            Calendar nascimento = Calendar.getInstance();
            nascimento.setTime(dataNasc);

            Calendar hoje = Calendar.getInstance();

            int anos = hoje.get(Calendar.YEAR) - nascimento.get(Calendar.YEAR);
            int meses = hoje.get(Calendar.MONTH) - nascimento.get(Calendar.MONTH);
            int dias = hoje.get(Calendar.DAY_OF_MONTH) - nascimento.get(Calendar.DAY_OF_MONTH);

            if (dias < 0) {
                meses--;
                hoje.add(Calendar.MONTH, -1);
                dias += hoje.getActualMaximum(Calendar.DAY_OF_MONTH);
            }

            if (meses < 0) {
                anos--;
                meses += 12;
            }

            return dias + " d " + meses + " m " + anos + " a";

        } catch (Exception e) {
            e.printStackTrace();
            return "Idade inválida";
        }
    }

}
