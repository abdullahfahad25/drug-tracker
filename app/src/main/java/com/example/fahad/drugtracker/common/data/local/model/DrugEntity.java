package com.example.fahad.drugtracker.common.data.local.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "drugs")
public class DrugEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String rxcui;
    private String name;
    private String source;

    public DrugEntity(String rxcui, String name, String source) {
        this.rxcui = rxcui;
        this.name = name;
        this.source = source;
    }

    public int getId() {
        return id;
    }

    public String getRxcui() {
        return rxcui;
    }

    public void setRxcui(String rxcui) {
        this.rxcui = rxcui;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @NonNull
    @Override
    public String toString() {
        return "DrugEntity{" +
                "id='" + id + '\'' +
                ", rxcui='" + rxcui + '\'' +
                ", name='" + name + '\'' +
                ", source='" + source + '\'' +
                '}';

    }
}
