package com.example.soninhotech.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Usuario")
public class Usuario {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "nome")
    public  String nome;
    @ColumnInfo(name = "email")
    public  String email;
    @ColumnInfo(name = "senha")
    public  String senha;


}