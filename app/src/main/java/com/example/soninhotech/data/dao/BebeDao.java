package com.example.soninhotech.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.soninhotech.data.entity.Bebe;

import java.util.List;

@Dao
public interface BebeDao {

    @Insert
    void insert(Bebe bebe);

    @Update
    void update(Bebe bebe);

    @Delete
    void delete(Bebe bebe);

    @Query("SELECT * FROM Bebe WHERE id = :id")
    Bebe getById(int id);

    @Query("SELECT * FROM Bebe ORDER BY nome ASC")
    List<Bebe> getAll();

    @Query("DELETE FROM Bebe")
    void deleteAll();
}
