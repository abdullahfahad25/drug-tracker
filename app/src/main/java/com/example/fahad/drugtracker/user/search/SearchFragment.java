package com.example.fahad.drugtracker.user.search;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.fahad.drugtracker.R;

public class SearchFragment extends Fragment {

    private EditText textSearch;
    private Button btnSearch;
    private RecyclerView recyclerView;
    private SearchResultAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_search, container, false);

        textSearch = root.findViewById(R.id.etSearch);
        btnSearch = root.findViewById(R.id.btnSearch);

        recyclerView = root.findViewById(R.id.rvResults);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter = new SearchResultAdapter();
        recyclerView.setAdapter(adapter);

        return root;
    }
}