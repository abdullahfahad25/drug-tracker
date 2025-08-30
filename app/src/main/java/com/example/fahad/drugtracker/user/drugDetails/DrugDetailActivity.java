package com.example.fahad.drugtracker.user.drugDetails;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.fahad.drugtracker.R;

public class DrugDetailActivity extends AppCompatActivity {
    private TextView name;
    private TextView description;
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug_detail);

        name = findViewById(R.id.drugName);
        description = findViewById(R.id.drugDescription);
        btnAdd = findViewById(R.id.addDrugBtn);
    }
}