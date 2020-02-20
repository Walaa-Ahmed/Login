package com.example.newdatabase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    EditText editTextUsername, editTextEmail, editTextPassword;
    Button buttonSave;
    DatabaseReference databaseReference;
    FirebaseDatabase database;
    TextView textViewGoLogin;
    Module module;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        buttonSave = findViewById(R.id.btn_save);
        editTextUsername = findViewById(R.id.edit_username);
        editTextEmail = findViewById(R.id.edit_email);
        editTextPassword = findViewById(R.id.edit_password);
        textViewGoLogin = findViewById(R.id.text_go_login);

        module = new Module();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference().child("Users");

        textViewGoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this, SignIn.class);
                startActivity(intent);
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                module.setUsername(editTextUsername.getText().toString());
                module.setEmail(editTextEmail.getText().toString());
                module.setPassword(editTextPassword.getText().toString());

                databaseReference.child(module.getUsername()).setValue(module).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(SignUp.this, "User created...", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignUp.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(SignUp.this, "Failed...", Toast.LENGTH_SHORT).show();
                        }

                    }
                });


            }
        });

    }
}
