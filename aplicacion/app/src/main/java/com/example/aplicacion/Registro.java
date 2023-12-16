package com.example.aplicacion;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Registro extends AppCompatActivity {
    private EditText editTextName, editTextEmail, editTextPassword;
    private Button buttonRegister, buttonBack;

    private FirebaseAuth mAuth;
    private DatabaseReference usersRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        mAuth = FirebaseAuth.getInstance();
        usersRef = FirebaseDatabase.getInstance().getReference().child("users");

        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonRegister = findViewById(R.id.buttonRegister);
        buttonBack = findViewById(R.id.buttonBack);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });


        // Configura el OnClickListener para el botón de retroceso
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); // Cierra la actividad actual y vuelve atrás
            }
        });
    }

    private void registerUser() {
        final String name = editTextName.getText().toString().trim();
        final String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
        } else {
                mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(Registro.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Registro exitoso
                                FirebaseUser currentUser = mAuth.getCurrentUser();
                                if (currentUser != null) {
                                    String userID = currentUser.getUid();
                                    DatabaseReference currentUserRef = usersRef.child(userID);

                                    String name = editTextName.getText().toString().trim();
                                    String email = editTextEmail.getText().toString().trim();

                                    Usuario newUser = new Usuario(name, email /* otros campos */);

                                    currentUserRef.setValue(newUser)
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        Toast.makeText(Registro.this, "Usuario registrado exitosamente", Toast.LENGTH_SHORT).show();
                                                        // Aquí puedes redirigir a otra actividad o realizar otras acciones después del registro exitoso
                                                    } else {
                                                        Toast.makeText(Registro.this, "Error al registrar usuario", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });
                                }
                            } else {
                                // Error en el registro
                                Toast.makeText(Registro.this, "Error al crear cuenta: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
    private void cerrarSesion() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        finish(); // Cierra la actividad actual
    }
}