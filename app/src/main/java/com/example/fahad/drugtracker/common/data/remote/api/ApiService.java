package com.example.fahad.drugtracker.common.data.remote.api;

import com.example.fahad.drugtracker.common.data.remote.model.DrugsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("REST/drugs.json")
    Call<DrugsResponse> getDrugs(@Query("name") String name, @Query("expand") String expand);
}
