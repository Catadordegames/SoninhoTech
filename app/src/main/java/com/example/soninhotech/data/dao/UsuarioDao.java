package com.example.soninhotech.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.soninhotech.data.entity.Usuario;

import java.util.List;

@Dao
public interface UsuarioDao {

    // Inserir um novo usuario
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUsuario(Usuario usuario);

    // Atualizar um usuario
    @Update
    void updateUsuario(Usuario usuario);

    // Deletar um usuario
    @Delete
    void deleteUsuario(Usuario usuario);

    // Buscar um usuario por id
    @Query("SELECT * FROM Usuario WHERE id = :id")
    Usuario getUsuarioById(int id);

    // Buscar todos os usuarios (opcional)
    @Query("SELECT * FROM Usuario")
    List<Usuario> getAllUsuarios();
}
