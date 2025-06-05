package com.example.soninhotech;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ConexaoDB extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "soninhoTechDB.db";
    private static final int DATABASE_VERSION = 1;

    private final Context context;
    private final String dbPath;

    public ConexaoDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        this.dbPath = context.getDatabasePath(DATABASE_NAME).getPath();

        // Realiza a cópia do banco de dados da pasta assets, se necessário.
        if (!checkDatabaseExists()) {
            copyDatabase();
        }
    }

    private boolean checkDatabaseExists() {
        File dbFile = context.getDatabasePath(DATABASE_NAME);
        return dbFile.exists();
    }

    private void copyDatabase() {
        try {
            InputStream inputStream = context.getAssets().open(DATABASE_NAME);
            // String outFileName = dbPath; // not strictly necessary, can use dbPath directly
            OutputStream outputStream = new FileOutputStream(dbPath);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }

            outputStream.flush();
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            // É uma boa prática incluir a exceção original
            throw new RuntimeException("Erro ao copiar o banco de dados: " + e.getMessage(), e);
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Não cria tabelas, banco já está pré-populado.
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Se houver mudança de versão, pode implementar lógica de atualização aqui.
    }

    /**
     * Retorna a instância de banco de dados legível/gravável.
     * Em Kotlin, a visibilidade padrão é 'public', então mantemos 'public' aqui.
     */
    public SQLiteDatabase openDatabase() {
        return SQLiteDatabase.openDatabase(
                dbPath,
                null,
                SQLiteDatabase.OPEN_READWRITE
        );
    }


}
