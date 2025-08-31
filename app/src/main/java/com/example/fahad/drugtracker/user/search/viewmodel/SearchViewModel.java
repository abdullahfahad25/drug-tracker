package com.example.fahad.drugtracker.user.search.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fahad.drugtracker.common.data.remote.model.DrugsResponse;
import com.example.fahad.drugtracker.common.repository.DrugRepository;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchViewModel extends AndroidViewModel {
    private final DrugRepository repository;
    private final MutableLiveData<List<DrugsResponse.ConceptProperty>> results = new MutableLiveData<>();

    public SearchViewModel(@NonNull Application application) {
        super(application);

        repository = new DrugRepository(application.getApplicationContext());
    }

    public LiveData<List<DrugsResponse.ConceptProperty>> getResults() {
        return results;
    }

    public void search(final String query) {
        repository.searchDrugs(query, new Callback<DrugsResponse>() {
            @Override
            public void onResponse(Call<DrugsResponse> call, Response<DrugsResponse> response) {
                List<DrugsResponse.ConceptProperty> flat = new ArrayList<>();
                if (response.isSuccessful()
                        && response.body() != null
                        && response.body().drugGroup != null
                        && response.body().drugGroup.conceptGroup != null) {
                    for (DrugsResponse.ConceptGroup g : response.body().drugGroup.conceptGroup) {
                        if (g != null
                                && "SBD".equalsIgnoreCase(g.tty)
                                && g.conceptProperties != null) {
                            flat.addAll(g.conceptProperties);
                        }
                    }

                    if (flat.size() > 10)
                        flat = flat.subList(0, 10);
                }
                results.postValue(flat);
            }


            @Override
            public void onFailure(Call<DrugsResponse> call, Throwable t) {
                results.postValue(new ArrayList<>());
            }
        });
    }
}
