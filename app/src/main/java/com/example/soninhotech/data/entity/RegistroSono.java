package com.example.soninhotech.data.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "RegistroSono",
        foreignKeys = @ForeignKey(entity = Bebe.class, parentColumns = "id", childColumns = "id_bebe", onDelete = ForeignKey.NO_ACTION),
        indices = { @Index(value = {"id_bebe"})}
)
public class RegistroSono {
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
}
