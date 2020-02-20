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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignIn extends AppCompatActivity {

    EditText editTextUsername, editTextPassword;
    Button buttonSignIn;
    TextView textViewGoSignUp;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        editTextUsername = findViewById(R.id.edit_username_sign_in);
        editTextPassword = findViewById(R.id.edit_password_sign_in);
        buttonSignIn = findViewById(R.id.btn_login);

        textViewGoSignUp = findViewById(R.id.text_go_sign_up);
        textViewGoSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignIn.this, SignUp.class);
                startActivity(intent);
            }
        });

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");

        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            String password;

            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString();
                password = editTextPassword.getText().toString();

                try {
                    databaseReference.child(username).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            Module module = dataSnapshot.getValue(Module.class);
                            if (password.equals(module.getPassword())) {
                                Toast.makeText(SignIn.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignIn.this, MainActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(SignIn.this, "Enter correct password", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                        }
                    });
                } catch (Exception ex) {
                    Toast.makeText(SignIn.this, "not found user..!", Toast.LENGTH_SHORT).show();
                    ex.printStackTrace();
                }
            }

        });

    }
}
