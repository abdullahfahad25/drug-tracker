package com.example.fahad.drugtracker.common.data.local;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.fahad.drugtracker.common.data.local.model.DrugEntity;

import java.util.List;

@Dao
public interface DrugDao {
    @Insert
    void insert(DrugEntity drug);

    @Delete
    void delete(DrugEntity drug);

    @Query("SELECT * FROM drugs")
    List<DrugEntity> getAll();

    @Query("SELECT COUNT(*) FROM drugs WHERE source = :source")
    int countBySource(String source);

    @Query("SELECT * FROM drugs WHERE rxcui = :rxcui LIMIT 1")
    DrugEntity findByRxcui(String rxcui);
}
