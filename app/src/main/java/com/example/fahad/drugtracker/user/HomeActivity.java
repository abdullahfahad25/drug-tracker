package com.example.fahad.drugtracker.user;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.fahad.drugtracker.R;
import com.example.fahad.drugtracker.user.search.ui.SearchFragment;
import com.example.fahad.drugtracker.user.userDrugs.ui.UserDrugsFragment;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Load TrackerFragment by default
        if (savedInstanceState == null) {
            loadFragment(new UserDrugsFragment(), false);
        }

        // Open SearchFragment when button clicked
        Button btnOpenSearch = findViewById(R.id.btnOpenSearch);
        btnOpenSearch.setOnClickListener(v -> {
            loadFragment(new SearchFragment(), true);
        });
    }

    public void loadFragment(Fragment fragment, boolean addToBackStack) {
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment);

        if (addToBackStack) {
            transaction.addToBackStack(null);
        }

        transaction.commit();
    }

    @Override
    public boolean onSupportNavigateUp() {
        getSupportFragmentManager().popBackStack();
        return true;
    }
}