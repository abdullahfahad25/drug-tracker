package com.example.fahad.drugtracker.user.userDrugs.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.fahad.drugtracker.common.data.local.model.DrugEntity;
import com.example.fahad.drugtracker.common.repository.DrugRepository;

import java.util.List;

public class UserDrugsViewModel extends AndroidViewModel {
    private final DrugRepository repository;
    private final MutableLiveData<List<DrugEntity>> drugs = new MutableLiveData<>();
    private final MutableLiveData<String> message = new MutableLiveData<>();

    public UserDrugsViewModel(@NonNull Application application) {
        super(application);
        repository = new DrugRepository(application.getApplicationContext());
    }

    public LiveData<List<DrugEntity>> getDrugs() {
        return drugs;
    }

    public LiveData<String> getMessage() {
        return message;
    }

    public void loadUserDrugs() {
        repository.getUserDrugs(result -> {
            drugs.postValue(result);
        });
    }

    public void deleteDrug(DrugEntity drug) {
        repository.deleteUserDrug(drug);
    }

    public void insertCustomDrug(final String name) {
        repository.countBySource("CUSTOM", count -> {
            if (count >= 3) {
                message.postValue("Max 3 CUSTOM drugs reached");
                return;
            }

            DrugEntity entity1 = new DrugEntity(null, name, "CUSTOM");
            repository.insertUserDrug(entity1);
            message.postValue("Added");
        });
    }
}
