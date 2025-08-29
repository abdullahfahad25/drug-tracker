package com.example.fahad.drugtracker.common.data.remote.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DrugsResponse {
    @SerializedName("drugGroup")
    public DrugGroup drugGroup;

    public static class DrugGroup {
        @SerializedName("conceptGroup")
        public List<ConceptGroup> conceptGroup;
    }

    public static class ConceptGroup {
        @SerializedName("tty")
        public String tty;
        @SerializedName("conceptProperties")
        public List<ConceptProperty> conceptProperties;
    }

    public static class ConceptProperty {
        @SerializedName("rxcui")
        public String rxcui;
        @SerializedName("name")
        public String name;
    }
}
