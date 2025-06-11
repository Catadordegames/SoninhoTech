package com.example.soninhotech.data.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Usuario")
public class Usuario {
    @PrimaryKey(autoGenerate = false)
    @NonNull
    public  String id;
    @ColumnInfo(name = "email")
    @NonNull
    public  String email;
    @ColumnInfo(name = "senha")
    @NonNull
    public  String senha;

    public Usuario(String id, String email, String senha){
        this.id = id;
        this.email = email;
        this.senha = senha;
    }


}