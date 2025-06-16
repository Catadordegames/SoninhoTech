package com.example.soninhotech.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.soninhotech.data.entity.RegistroSono;

import java.util.List;

@Dao
public interface RegistroSonoDao {

    @Insert
    void insert(RegistroSono registroSono);

    @Update
    void update(RegistroSono registroSono);

    @Delete
    void delete(RegistroSono registroSono);

    @Query("SELECT * FROM RegistroSono WHERE id = :id")
    RegistroSono getById(int id);

    @Query("SELECT * FROM RegistroSono WHERE id_bebe = :id_bebe ORDER BY inicio DESC")
    List<RegistroSono> getAll(int id_bebe);

    @Query("DELETE FROM RegistroSono")
    void deleteAll();
}
