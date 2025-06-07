package com.example.soninhotech.data;

import android.content.Context;

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
        version = 1,
        exportSchema = false
)
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase INSTANCE;

    // DAOs
    public abstract UsuarioDao usuarioDao();
    public abstract BebeDao bebeDao();
    public abstract SexoDao sexoDao();
    public abstract RegistroSonoDao registroSonoDao();
    public abstract RegistroAlimentacaoDao registroAlimentacaoDao();

    // Singleton
    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    AppDatabase.class,
                                    "soninhoTechDB.db"
                            )
                            .createFromAsset("soninhoTechDB.db") // Usa o banco pré-populado da pasta assets
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
