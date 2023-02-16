package com.example.cs360inventoryapp.login;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cs360inventoryapp.R;
import com.example.cs360inventoryapp.data.DatabaseManager;
import com.example.cs360inventoryapp.main.MainActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText txtUsername;
    private EditText txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);

        Button btnLogin = (Button)findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(l -> handleLogin());
    }

    private void handleLogin() {
        String username = txtUsername.getText().toString();
        String password = txtPassword.getText().toString();

        if (DatabaseManager.getInstance(getApplicationContext()).authenticate(username, password)) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "Invalid credentials", Toast.LENGTH_LONG).show();
        }
    }
}