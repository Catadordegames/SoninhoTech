package com.example.soninhotech.data;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.soninhotech.data.dao.BebeDao;
import com.example.soninhotech.data.dao.UsuarioDao;
import com.example.soninhotech.data.dao.SexoDao;
import com.example.soninhotech.data.dao.RegistroSonoDao;
import com.example.soninhotech.data.dao.RegistroAlimentacaoDao;
import com.example.soninhotech.data.entity.Bebe;
import com.example.soninhotech.data.entity.Usuario;
import com.example.soninhotech.data.entity.Sexo;
import com.example.soninhotech.data.entity.RegistroSono;
import com.example.soninhotech.data.entity.RegistroAlimentacao;

@Database(
        entities = {
                Usuario.class,
                Bebe.class,
                Sexo.class,
                RegistroSono.class,
                RegistroAlimentacao.class
        },
        version = 2,
        exportSchema = false
)
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase INSTANCE;

    public abstract UsuarioDao usuarioDao();
    public abstract BebeDao bebeDao();
    public abstract SexoDao sexoDao();
    public abstract RegistroSonoDao registroSonoDao();
    public abstract RegistroAlimentacaoDao registroAlimentacaoDao();

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    SharedPreferences prefs = context.getSharedPreferences("APP_PREFS", Context.MODE_PRIVATE);
                    boolean isFirstRun = prefs.getBoolean("IS_FIRST_RUN", true);

                    RoomDatabase.Builder<AppDatabase> builder = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "soninhoTech.db" // Nome do banco interno
                    );

                    if (isFirstRun) {
                        builder = builder.createFromAsset("soninhoTech.db");

                        // Marcar que já rodou uma vez
                        prefs.edit().putBoolean("IS_FIRST_RUN", false).apply();
                    }

                    INSTANCE = builder.build();
                }
            }
        }
        return INSTANCE;
    }
}
