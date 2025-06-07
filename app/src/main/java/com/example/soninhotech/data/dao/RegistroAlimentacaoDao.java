package com.example.soninhotech.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.soninhotech.data.entity.RegistroAlimentacao;

import java.util.List;

@Dao
public interface RegistroAlimentacaoDao {

    @Insert
    void insert(RegistroAlimentacao registroAlimentacao);

    @Update
    void update(RegistroAlimentacao registroAlimentacao);

    @Delete
    void delete(RegistroAlimentacao registroAlimentacao);

    @Query("SELECT * FROM RegistroAlimentacao WHERE id = :id")
    RegistroAlimentacao getById(int id);

    @Query("SELECT * FROM RegistroAlimentacao ORDER BY inicio DESC")
    List<RegistroAlimentacao> getAll();

    @Query("DELETE FROM RegistroAlimentacao")
    void deleteAll();
}
