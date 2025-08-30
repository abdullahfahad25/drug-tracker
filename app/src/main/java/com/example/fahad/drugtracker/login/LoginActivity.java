package com.example.fahad.drugtracker.login;

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
import com.example.fahad.drugtracker.user.HomeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";

    private EditText email;
    private EditText password;
    private Button btnLogin;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.emailLogin);
        password = findViewById(R.id.passwordLogin);
        btnLogin = findViewById(R.id.loginBtn);
        auth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(view -> doLogin());
    }

    private void doLogin() {
        String email = this.email.getText().toString().trim();
        String pass = password.getText().toString().trim();
        if (email.isEmpty() || pass.isEmpty()) {
            Toast.makeText(this, "Fill fields", Toast.LENGTH_SHORT).show(); return;
        }


        auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this, "Auth failed: "
                                    + (task.getException() != null ? task.getException().getMessage() : "")
                                    , Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }
}