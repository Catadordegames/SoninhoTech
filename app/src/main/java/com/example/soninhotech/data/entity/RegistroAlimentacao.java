package com.example.soninhotech.data.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(
        tableName = "RegistroAlimentacao",
        foreignKeys = @ForeignKey(entity = Bebe.class, parentColumns = "id", childColumns = "id_bebe", onDelete = ForeignKey.NO_ACTION),
        indices = { @Index(value = {"id_bebe"})}

)
public class RegistroAlimentacao implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "id_bebe")
    @NonNull
    public int idBebe;
    @ColumnInfo(name = "inicio")
    @NonNull
    public  String inicio;
    @ColumnInfo(name = "fim")
    public  String fim;

    public RegistroAlimentacao(int id, int idBebe, String inicio, String fim){
        this.id = id;
        this.idBebe = idBebe;
        this.inicio = inicio;
        this.fim = fim;
    }

    @Ignore
    public RegistroAlimentacao(int idBebe, String inicio, String fim){
        this.idBebe = idBebe;
        this.inicio = inicio;
        this.fim = fim;
    }
}
