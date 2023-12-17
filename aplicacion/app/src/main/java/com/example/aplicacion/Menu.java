package com.example.aplicacion;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button cerrarSesionButton = findViewById(R.id.buttonCerrarSesion);
        cerrarSesionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cerrarSesion();
            }
        });
    }

    private void cerrarSesion() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        finish(); // Cierra la actividad actual
    }

    public void ingresando(View view){
        Intent ingresarAPrimerVista = new Intent(this, VistaDos.class);
        startActivity(ingresarAPrimerVista);
    }

    public void completadas(View view){
        Intent completadas = new Intent(this, Completadas.class);
        startActivity(completadas);
    }

    public void Imagen(View view){
        Intent img = new Intent(this, Imagen.class);
        startActivity(img);
    }
    public void actualizarInfo(View view){
        Intent info = new Intent(this, ActualizarInformacionActivity.class);
        startActivity(info);
    }
}

