package com.example.soninhotech.repository;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;
import com.example.soninhotech.data.AppDatabase;

public class MeuApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // Só para garantir que o banco está pronto ao iniciar (opcional)
        AppDatabase.getInstance(this);
    }

    public static AppDatabase getDatabase(Context context) {
        return AppDatabase.getInstance(context);
    }
}

