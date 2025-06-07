package com.example.soninhotech.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "RegistroSono",
        foreignKeys = @ForeignKey(entity = Bebe.class, parentColumns = "id", childColumns = "id_bebe", onDelete = ForeignKey.CASCADE)
)
public class RegistroSono {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "id_bebe")
    public int idBebe;
    @ColumnInfo(name = "inicio")
    public  String inicio;
    @ColumnInfo(name = "fim")
    public  String fim;
}
