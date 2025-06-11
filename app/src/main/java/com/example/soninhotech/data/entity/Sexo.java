package com.example.soninhotech.data.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Sexo")
public class Sexo {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "sexo")
    @NonNull
    public String sexo;

}
