package com.example.aplicacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }


    public void ingresando(View view){
        Intent ingresarAPrimerVista =new Intent(this,VistaDos.class);
        startActivity(ingresarAPrimerVista);
    }

    public void completadas(View view){
        Intent completadas =new Intent(this,Completadas.class);
        startActivity(completadas);
    }
    public void Imagen(View view){
        Intent img =new Intent(this, Imagen.class);
        startActivity(img);
    }
}