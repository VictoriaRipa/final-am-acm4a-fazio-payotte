package com.example.aplicacion;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ActualizarInformacionActivity extends AppCompatActivity {

    private TextView textViewEmail;
    private TextView textViewUserName;
    private EditText editTextNewName;
    private Button buttonUpdate;
    private Button backButton;

    private FirebaseAuth mAuth;
    private DatabaseReference usersRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_informacion);
// Inicialización de Firebase y vistas
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        usersRef = FirebaseDatabase.getInstance().getReference().child("users");

        textViewEmail = findViewById(R.id.textViewEmail);
        textViewUserName = findViewById(R.id.textViewUserName);
        editTextNewName = findViewById(R.id.editTextNewName);
        buttonUpdate = findViewById(R.id.buttonUpdate);
        backButton = findViewById(R.id.backButton);

        // Configuración del botón para volver al menú principal

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lógica para regresar al menú principal
                Intent menu = new Intent(ActualizarInformacionActivity.this, Menu.class);
                startActivity(menu);
                finish();
            }
        });
        // Verifica si el usuario está autenticado

        if (currentUser != null) {
            String userID = currentUser.getUid();

            // traer el correo electrónico del usuario actual
            String email = currentUser.getEmail();
            textViewEmail.setText(email);

            // traer el nombre del usuario desde la base de datos
            DatabaseReference currentUserRef = usersRef.child(userID);
            currentUserRef.child("nombre").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        String userName = dataSnapshot.getValue(String.class);
                        textViewUserName.setText(userName);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(ActualizarInformacionActivity.this, "Error al traer información de la base de datos", Toast.LENGTH_SHORT).show();
                }
            });
            // Configuración del botón para actualizar información

            buttonUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String newName = editTextNewName.getText().toString().trim();

                    if (!newName.isEmpty()) {
                        updateUserInformation(newName);
                        Intent menu = new Intent(ActualizarInformacionActivity.this, Menu.class);
                        startActivity(menu);
                        finish(); // Opcional, dependiendo de la navegación que desees
                    } else {
                        Toast.makeText(ActualizarInformacionActivity.this, "Por favor, ingresa un nuevo nombre", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            Toast.makeText(ActualizarInformacionActivity.this, "Error: el usuario no está logueado o cerró la sesión", Toast.LENGTH_SHORT).show();
        }
    }
    // Método para actualizar la información del usuario en la base de datos

    private void updateUserInformation(String newName) {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            String userID = currentUser.getUid();
            DatabaseReference currentUserRef = usersRef.child(userID).child("nombre");

            currentUserRef.setValue(newName)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(ActualizarInformacionActivity.this, "Nombre actualizado correctamente", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(ActualizarInformacionActivity.this, "Error al actualizar el nombre", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}
