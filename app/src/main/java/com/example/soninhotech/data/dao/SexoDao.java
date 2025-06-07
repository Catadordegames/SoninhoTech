package com.example.soninhotech.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.soninhotech.data.entity.Sexo;

import java.util.List;

@Dao
public interface SexoDao {

    // Inserir um novo Sexo
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSexo(Sexo sexo);

    // Buscar todos os sexos (útil para preencher um Spinner, por exemplo)
    @Query("SELECT * FROM Sexo")
    List<Sexo> getAllSexos();

    // Buscar um Sexo por id (exemplo que você pode usar na tela do bebê)
    @Query("SELECT * FROM Sexo WHERE id = :idSexo")
    Sexo getSexoById(int idSexo);

    // (Opcional) Deletar um sexo (se você quiser permitir isso)
    @Query("DELETE FROM Sexo WHERE id = :idSexo")
    void deleteSexoById(int idSexo);
}