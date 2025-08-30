package com.example.fahad.drugtracker.common.repository;

import com.example.fahad.drugtracker.common.data.remote.api.ApiService;
import com.example.fahad.drugtracker.common.data.remote.api.RetrofitInstance;
import com.example.fahad.drugtracker.common.data.remote.model.DrugsResponse;

import retrofit2.Callback;

public class DrugRepository {
    private final ApiService apiService;

    public DrugRepository() {
        apiService = RetrofitInstance.getApiService();
    }

    // Network call (asynchronous) to search drugs
    public void searchDrugs(final String name, final Callback<DrugsResponse> cb) {
        apiService.getDrugs(name, "psn").enqueue(cb);
    }
}
