package com.example.fahad.drugtracker.user.drugDetails;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.fahad.drugtracker.common.data.local.model.DrugEntity;
import com.example.fahad.drugtracker.common.repository.DrugRepository;

public class DrugDetailsViewModel extends AndroidViewModel {
    private final DrugRepository repository;
    private final MutableLiveData<String> message = new MutableLiveData<>();

    public DrugDetailsViewModel(@NonNull Application application) {
        super(application);
        repository = new DrugRepository(application.getApplicationContext());
    }

    public LiveData<String> getMessage() {
        return message;
    }

    public void insertDrugs(final String name, final String rxcui) {
        repository.countBySource("API", count -> {
            if (count >= 7) {
                message.postValue("Max 7 API drugs reached");
                return;
            }

            repository.findByRxcui(rxcui, entity -> {
                if (entity != null) {
                    message.postValue("Already added");
                    return;
                }

                DrugEntity entity1 = new DrugEntity(rxcui, name, "API");
                repository.insertUserDrug(entity1);
                message.postValue("Added");
            });
        });
    }
}
