package com.example.remindmeeasy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        EditText username = findViewById(R.id.username);
        EditText email = findViewById(R.id.email);
        EditText password =  findViewById(R.id.password);
        MaterialButton submit = findViewById(R.id.submit);
        TextView login =  findViewById(R.id.login);
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs",MODE_PRIVATE);

        submit.setOnClickListener(view -> {
            String userName = username.getText().toString();
            String Email = email.getText().toString();
            String Password = password.getText().toString();

            String hashedPassword = hashPassword(Password);

            if (hashedPassword != null) {
                // Save user data to SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("username", userName);
                editor.putString("email", Email);
                editor.putString("password", hashedPassword);
                editor.apply();

                // Show success message
                Toast.makeText(SignUp.this, "Sign up successful!", Toast.LENGTH_SHORT).show();
            } else {
                // Show error message
                Toast.makeText(SignUp.this, "Error hashing password.", Toast.LENGTH_SHORT).show();
            }
        });



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUp.this, LogIn.class));
            }
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
