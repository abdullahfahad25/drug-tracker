package com.example.fahad.drugtracker.common.data.local;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

public abstract class DrugDatabase extends RoomDatabase {
    private DrugDatabase() {}

    public abstract DrugDao drugDao();

    private static DrugDatabase instance;

    public static DrugDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (DrugDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            DrugDatabase.class,
                            "drug_db"
                    ).build();
                }
            }
        }
        return instance;
    }
}
