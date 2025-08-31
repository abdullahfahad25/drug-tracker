package com.example.fahad.drugtracker.user.userDrugs.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fahad.drugtracker.R;
import com.example.fahad.drugtracker.common.data.local.model.DrugEntity;
import com.example.fahad.drugtracker.user.drugDetails.DrugDetailActivity;
import com.example.fahad.drugtracker.user.userDrugs.viewmodel.UserDrugsViewModel;

import java.util.List;

public class UserDrugsFragment extends Fragment {
    private RecyclerView recyclerView;
    private Button btnCustomAdd;
    private UserDrugsAdapter adapter;
    private UserDrugsViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_user_drugs, container, false);

        viewModel = new ViewModelProvider(this).get(UserDrugsViewModel.class);

        btnCustomAdd = root.findViewById(R.id.btnCustom);

        recyclerView = root.findViewById(R.id.trackerRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter = new UserDrugsAdapter();
        recyclerView.setAdapter(adapter);

        setupObservers();
        setupSwipeDeleteCallback();
        setupClickListeners();

        viewModel.loadUserDrugs();

        return root;
    }

    private void setupClickListeners() {
        btnCustomAdd.setOnClickListener(v -> {
            EditText input = new EditText(getContext());
            new AlertDialog.Builder(getContext())
                    .setTitle("Add Custom Medication")
                    .setView(input)
                    .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String name = input.getText().toString().trim();
                            if (name != null && !name.isEmpty()) {
                                viewModel.insertCustomDrug(name);
                            }
                        }
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
        });
    }

    private void setupObservers() {
        viewModel.getDrugs().observe(getViewLifecycleOwner(), new Observer<List<DrugEntity>>() {
            @Override
            public void onChanged(List<DrugEntity> drugEntities) {
                adapter.setItems(drugEntities);
            }
        });

        viewModel.getMessage().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
                if (s.equals("Added")) {
                    viewModel.loadUserDrugs();
                }
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
                viewModel.loadUserDrugs();
            }
        };

        new ItemTouchHelper(simpleCallback).attachToRecyclerView(recyclerView);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        viewModel.getDrugs().removeObservers(getViewLifecycleOwner());
        viewModel.getMessage().removeObservers(getViewLifecycleOwner());
    }
}