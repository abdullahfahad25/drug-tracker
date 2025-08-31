package com.example.fahad.drugtracker.user.drugDetails;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.fahad.drugtracker.R;
import com.google.android.material.appbar.MaterialToolbar;

public class DrugDetailActivity extends AppCompatActivity {
    private static final String TAG = "DrugDetailActivity";
    private TextView name;
    private TextView description;
    private Button btnAdd;
    private MaterialToolbar toolbar;
    private DrugDetailsViewModel viewModel;

    private String rxcui;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate:");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug_detail);

        viewModel = new ViewModelProvider(this).get(DrugDetailsViewModel.class);

        name = findViewById(R.id.drugName);
        description = findViewById(R.id.drugDescription);
        btnAdd = findViewById(R.id.addDrugBtn);
        toolbar = findViewById(R.id.drugDetailAppBar);

        Intent intent = getIntent();
        String extraName = intent.getStringExtra("extra_name");
        rxcui = intent.getStringExtra("extra_rxcui");
        Log.d(TAG, "onCreate: name: " + name + ", rxcui: " + rxcui);

        setupObservers();
        setupCLickListeners();
        updateValues(extraName, rxcui);
    }

    private void setupObservers() {
        viewModel.getMessage().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(DrugDetailActivity.this, s, Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void setupCLickListeners() {
        btnAdd.setOnClickListener(v -> {
            viewModel.insertDrugs(name.getText().toString(), rxcui);
        });

        toolbar.setNavigationOnClickListener(v -> {
            finish();
        });
    }

    private void updateValues(String extraName, String rxcui) {
        name.setText(extraName);

        String dummyDescription = "Paracetamol is a common pain reliever and a fever reducer. " +
                "It is used to treat many conditions such as headache, muscle aches, arthritis, backache, toothaches, colds, and fevers. " +
                "It works by blocking the production of certain chemicals in the brain that signal pain and fever. " +
                "Do not exceed the recommended dose as it may cause liver damage. " +
                "\n\nDosage:\n- Adults: 500mg to 1000mg every 4-6 hours as needed (max 4000mg/day)\n" +
                "- Children: Dose based on weight and age, follow pediatrician advice\n\n" +
                "Side effects are rare but may include rash, nausea, or allergic reactions. " +
                "Always read the package instructions carefully. " +
                "If symptoms persist or worsen, consult a healthcare provider immediately. " +
                "\n\nLorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. " +
                "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. " +
                "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";


        String desc = rxcui + "\n" + dummyDescription;
        description.setText(desc);
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy:");
        super.onDestroy();
        viewModel.getMessage().removeObservers(this);
    }
}