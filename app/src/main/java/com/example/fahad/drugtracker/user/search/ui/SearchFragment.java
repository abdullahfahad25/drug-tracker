package com.example.fahad.drugtracker.user.search.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.fahad.drugtracker.R;
import com.example.fahad.drugtracker.common.data.remote.model.DrugsResponse;
import com.example.fahad.drugtracker.user.drugDetails.DrugDetailActivity;
import com.example.fahad.drugtracker.user.search.viewmodel.SearchViewModel;
import com.google.android.material.appbar.MaterialToolbar;

public class SearchFragment extends Fragment {

    private EditText textSearch;
    private Button btnSearch;
    private MaterialToolbar toolbar;
    private RecyclerView recyclerView;
    private SearchResultAdapter adapter;
    private SearchViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_search, container, false);

        viewModel = new ViewModelProvider(this).get(SearchViewModel.class);

        textSearch = root.findViewById(R.id.etSearch);
        btnSearch = root.findViewById(R.id.btnSearch);
        toolbar = root.findViewById(R.id.searchAppBar);

        recyclerView = root.findViewById(R.id.rvResults);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter = new SearchResultAdapter();
        recyclerView.setAdapter(adapter);

        setupClickListeners();
        setupObservers();

        return root;
    }

    private void setupClickListeners() {
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = textSearch.getText().toString().trim();
                if (query == null || query.isEmpty())
                    return;

                viewModel.search(query);
            }
        });

        adapter.setListener(new SearchResultAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(DrugsResponse.ConceptProperty item) {
                Intent intent = new Intent(getActivity(), DrugDetailActivity.class);
                intent.putExtra("extra_name", item.name);
                intent.putExtra("extra_rxcui", item.rxcui);
                startActivity(intent);
            }
        });

        toolbar.setNavigationOnClickListener(v -> {
            requireActivity().getSupportFragmentManager().popBackStack();
        });
    }

    private void setupObservers() {
        viewModel.getResults().observe(getViewLifecycleOwner(), conceptProperties -> {
            adapter.setItems(conceptProperties);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        viewModel.getResults().removeObservers(getViewLifecycleOwner());
    }
}