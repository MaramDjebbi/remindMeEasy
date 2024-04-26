package com.example.remindmeeasy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LogIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        EditText email = findViewById(R.id.email);
        EditText password = findViewById(R.id.password);
        MaterialButton loginButton = findViewById(R.id.login);
        TextView signUp = findViewById(R.id.signup);

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        loginButton.setOnClickListener(view -> {
            String emailInput = email.getText().toString();
            String passwordInput = password.getText().toString();

            // Retrieve stored email and hashed password from SharedPreferences
            String storedEmail = sharedPreferences.getString("email", "");
            String storedPassword = sharedPreferences.getString("password", "");

            // Hash the input password for comparison
            String hashedInputPassword = hashPassword(passwordInput);

            if (storedEmail.equals(emailInput) && storedPassword.equals(hashedInputPassword)) {
                // Successful login
                Toast.makeText(LogIn.this, "Login successful!", Toast.LENGTH_SHORT).show();

                // Start the main activity or any other activity after login
                startActivity(new Intent(LogIn.this, dashBoard.class));
                finish(); // Close the login activity
            } else {
                // Incorrect email or password
                Toast.makeText(LogIn.this, "Incorrect email or password.", Toast.LENGTH_SHORT).show();
            }
        });

        signUp.setOnClickListener(view -> {
            startActivity(new Intent(LogIn.this, SignUp.class));
            finish(); // Close the login activity
        });
    }

    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = digest.digest(password.getBytes());

            // Convert byte array to hexadecimal string
            StringBuilder stringBuilder = new StringBuilder();
            for (byte b : hashedBytes) {
                stringBuilder.append(String.format("%02x", b));
            }

            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
