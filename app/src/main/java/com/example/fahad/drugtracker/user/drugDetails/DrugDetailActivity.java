package com.example.fahad.drugtracker.user.drugDetails;

import android.content.Intent;
import android.os.Bundle;
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

public class DrugDetailActivity extends AppCompatActivity {
    private TextView name;
    private TextView description;
    private Button btnAdd;
    private DrugDetailsViewModel viewModel;

    private String rxcui;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug_detail);

        viewModel = new ViewModelProvider(this).get(DrugDetailsViewModel.class);

        name = findViewById(R.id.drugName);
        description = findViewById(R.id.drugDescription);
        btnAdd = findViewById(R.id.addDrugBtn);

        Intent intent = getIntent();
        String extraName = intent.getStringExtra("extra_name");
        rxcui = intent.getStringExtra("extra_rxcui");

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
    }

    private void updateValues(String extraName, String rxcui) {
        name.setText(extraName);
        String desc = rxcui + "Dummy Data";
        description.setText(desc);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.getMessage().removeObservers(this);
    }
}