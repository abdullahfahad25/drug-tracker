package com.example.fahad.drugtracker.common.data.remote.api;

import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static final String BASE_URL = "https://rxnav.nlm.nih.gov/";
    private static ApiService apiService;

    private RetrofitInstance() {}

    public static ApiService getApiService() {
        if (apiService == null) {
            synchronized (RetrofitInstance.class) {
                if (apiService == null) {
                    apiService = new retrofit2.Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
                            .create(ApiService.class);
                }
            }
        }
        return apiService;
    }
}
