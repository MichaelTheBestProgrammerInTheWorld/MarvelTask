package com.michaelmagdy.marveltask.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MarvelDao {

    @Insert
    void insert(Marvel marvel);

    @Delete
    void delete(Marvel marvel);

    @Query("SELECT * FROM Marvel")
    LiveData<List<Marvel>> getAllMarvels();
}
