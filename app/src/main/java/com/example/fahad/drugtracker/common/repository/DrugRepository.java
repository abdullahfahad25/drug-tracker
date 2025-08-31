package com.example.fahad.drugtracker.common.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.fahad.drugtracker.common.data.local.DrugDao;
import com.example.fahad.drugtracker.common.data.local.DrugDatabase;
import com.example.fahad.drugtracker.common.data.local.model.DrugEntity;
import com.example.fahad.drugtracker.common.data.remote.api.ApiService;
import com.example.fahad.drugtracker.common.data.remote.api.RetrofitInstance;
import com.example.fahad.drugtracker.common.data.remote.model.DrugsResponse;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import retrofit2.Callback;

public class DrugRepository {
    private static final String TAG = "DrugRepository";

    private final ApiService apiService;
    private final DrugDao drugDao;

    // internal executor for quick background tasks
    private final ExecutorService executorService;

    public interface ResultCallback {
        void onResult(int value);
    }

    public interface FinderCallback {
        void onFound(DrugEntity entity);
    }

    public interface CompleteCallback<T> {
        void onComplete(T result);
    }

    public DrugRepository(Context context) {
        apiService = RetrofitInstance.getApiService();
        drugDao = DrugDatabase.getInstance(context).drugDao();
        executorService = Executors.newSingleThreadExecutor();
    }

    // Network call (asynchronous) to search drugs
    public void searchDrugs(final String name, final Callback<DrugsResponse> cb) {
        apiService.getDrugs(name, "psn").enqueue(cb);
    }

    public void getUserDrugs(CompleteCallback<List<DrugEntity>> cb) {
        executorService.submit(() -> {
            List<DrugEntity> list = drugDao.getAll();
            cb.onComplete(list);
        });
    }

    public void insertUserDrug(final DrugEntity drug) {
        executorService.submit(() -> {
            drugDao.insert(drug);
        });
    }

    public void deleteUserDrug(final DrugEntity drug) {
        executorService.submit(() -> {
            drugDao.delete(drug);
        });
    }

    // Count by source (call returns asynchronously through callback)
    public void countBySource(final String source, final ResultCallback cb) {
        //TODO: Must use Listenable future to handle UI updates
        executorService.submit(() -> {
            int count = drugDao.countBySource(source);
            if (cb != null)
                cb.onResult(count);
        });
    }

    public void findByRxcui(final String rxcui, final FinderCallback cb) {
        //TODO: Must use Listenable future to handle UI updates
        executorService.submit(() -> {
            DrugEntity e = drugDao.findByRxcui(rxcui);
            cb.onFound(e);
        });
    }
}
