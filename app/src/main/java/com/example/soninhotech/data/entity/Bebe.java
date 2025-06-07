package com.example.soninhotech.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.File;

@Entity(
        tableName = "Bebe",
        foreignKeys = {
                @ForeignKey(
                        entity = Usuario.class,
                        parentColumns = "id",
                        childColumns = "id_usuario",
                        onDelete = ForeignKey.CASCADE
                ),
                @ForeignKey(
                        entity = Sexo.class,
                        parentColumns = "id",
                        childColumns = "id_sexo",
                        onDelete = ForeignKey.SET_DEFAULT
                )
        }
)
public class Bebe {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "id_usuario")
    public int idUsuario;

    @ColumnInfo(name = "id_sexo", defaultValue = "1")
    public int idSexo;

    @ColumnInfo(name = "nome")
    public String nome;

    @ColumnInfo(name = "nascimento")
    public String dataNascimento;

    @ColumnInfo(name = "foto")
    public File foto;


}
