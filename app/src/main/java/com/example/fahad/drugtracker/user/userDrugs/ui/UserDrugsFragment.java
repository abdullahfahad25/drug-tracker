package com.example.fahad.drugtracker.user.userDrugs.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fahad.drugtracker.R;
import com.example.fahad.drugtracker.common.data.local.model.DrugEntity;
import com.example.fahad.drugtracker.user.userDrugs.viewmodel.UserDrugsViewModel;

import java.util.List;

public class UserDrugsFragment extends Fragment {
    private RecyclerView recyclerView;
    private UserDrugsAdapter adapter;
    private UserDrugsViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_user_drugs, container, false);

        viewModel = new ViewModelProvider(this).get(UserDrugsViewModel.class);

        recyclerView = root.findViewById(R.id.trackerRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter = new UserDrugsAdapter();
        recyclerView.setAdapter(adapter);

        setupObservers();
        setupSwipeDeleteCallback();

        viewModel.loadUserDrugs();

        return root;
    }

    private void setupObservers() {
        viewModel.getDrugs().observe(getViewLifecycleOwner(), new Observer<List<DrugEntity>>() {
            @Override
            public void onChanged(List<DrugEntity> drugEntities) {
                adapter.setItems(drugEntities);
            }
        });
    }

    private void setupSwipeDeleteCallback() {
        // Swipe-to-delete
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder
                    , @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int pos = viewHolder.getAdapterPosition();
                DrugEntity drug = adapter.getItemAt(pos);
                viewModel.deleteDrug(drug);
            }
        };

        new ItemTouchHelper(simpleCallback).attachToRecyclerView(recyclerView);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        viewModel.getDrugs().removeObservers(getViewLifecycleOwner());
    }
}