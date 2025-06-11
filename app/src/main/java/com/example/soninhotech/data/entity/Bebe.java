package com.example.soninhotech.data.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.File;

@Entity(
        tableName = "Bebe",
        foreignKeys = {
                @ForeignKey(
                        entity = Usuario.class,
                        parentColumns = "id",
                        childColumns = "id_usuario",
                        onDelete = ForeignKey.NO_ACTION
                ),
                @ForeignKey(
                        entity = Sexo.class,
                        parentColumns = "id",
                        childColumns = "id_sexo",
                        onDelete = ForeignKey.NO_ACTION
                )
        },
        indices = {
                @Index(value = {"id_usuario"}),
                @Index(value ={"id_sexo"})
        }
)
public class Bebe {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "id_usuario")
    @NonNull
    public String idUsuario;

    @ColumnInfo(name = "id_sexo")
    @NonNull
    public int idSexo;

    @ColumnInfo(name = "nome")
    @NonNull
    public String nome;

    @ColumnInfo(name = "nascimento")
    @NonNull
    public String dataNascimento;

    @ColumnInfo(name = "foto")
    public String foto;

    public Bebe(String idUsuario, String nome, int idSexo, String dataNascimento, String foto) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.idSexo = idSexo;
        this.dataNascimento = dataNascimento;
        this.foto = foto;
    }


}
