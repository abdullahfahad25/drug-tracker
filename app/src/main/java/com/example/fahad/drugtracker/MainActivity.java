package com.example.fahad.drugtracker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.fahad.drugtracker.login.LoginActivity;
import com.example.fahad.drugtracker.signup.SignupActivity;

public class MainActivity extends AppCompatActivity {

    private TextView alreadyAcc;
    private Button createAcc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alreadyAcc = findViewById(R.id.alreadyAcc);
        createAcc = findViewById(R.id.newAcc);

        setupClickListeners();
    }

    private void setupClickListeners() {
        alreadyAcc.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        createAcc.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SignupActivity.class);
            startActivity(intent);
        });
    }
}