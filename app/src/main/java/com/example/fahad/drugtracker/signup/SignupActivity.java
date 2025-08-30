package com.example.fahad.drugtracker.signup;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.fahad.drugtracker.R;
import com.example.fahad.drugtracker.login.LoginActivity;
import com.example.fahad.drugtracker.user.HomeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {
    private static final String TAG = "SignupActivity";

    private EditText name;
    private EditText email;
    private EditText password;
    private Button btnSignup;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        name = findViewById(R.id.nameSignup);
        email = findViewById(R.id.emailSignup);
        password = findViewById(R.id.passwordSignup);
        btnSignup = findViewById(R.id.signupBtn);
        auth = FirebaseAuth.getInstance();

        btnSignup.setOnClickListener(view -> doSignup());
    }

    private void doSignup() {
        String email = this.email.getText().toString().trim();
        String pass = password.getText().toString().trim();
        String name = this.name.getText().toString().trim();
        if (email.isEmpty() || pass.isEmpty() || name.isEmpty()) {
            Toast.makeText(this, "Fill fields", Toast.LENGTH_SHORT).show(); return;
        }


        auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                            finish();
                        } else {
                            Toast.makeText(SignupActivity.this, "Signup failed: "
                                            + (task.getException() != null ? task.getException().getMessage() : "")
                                    , Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }
}