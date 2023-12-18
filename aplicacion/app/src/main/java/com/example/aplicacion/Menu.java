package com.example.aplicacion;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class Menu extends AppCompatActivity {

    private String miInfo;

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

        // restaurar los datos si no es nulo
        if (savedInstanceState != null) {
            miInfo = savedInstanceState.getString("miInfo");
        }

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        // Guardar datos  al salir de la actividad
        // Guardar la información actual en miInfo
        miInfo = "Aquí guardas tu información actual";

        // Guardar en el Bundle
        outState.putString("miInfo", miInfo);
    }

    private void cerrarSesion() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        finish(); // Cierra la actividad actual
    }

    public void ingresando(View view){
        Intent ingresarAPrimerVista = new Intent(this, ListarTareas.class);
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
