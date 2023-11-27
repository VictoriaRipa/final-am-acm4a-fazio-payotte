package com.example.listmaker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Completadas extends AppCompatActivity {

    private List<String>completadas = new ArrayList<>();
    private ArrayAdapter<String> completadasAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completadas);

        completadasAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, completadas);
        ListView completadasListView = findViewById(R.id.completadasListView);
        completadasListView.setAdapter(completadasAdapter);

        // Obtener la lista de tareas completadas del Intent
        List<String> completadasList = getIntent().getStringArrayListExtra("completadas");
        if (completadasList != null) {
            // Agregar las tareas completadas a la lista
            completadas.addAll(completadasList);
            // Notificar al adaptador que los datos han cambiado
            completadasAdapter.notifyDataSetChanged();
        }
    }


    public void agregarALista(String ag) {
        completadas.add(ag);
        completadasAdapter.notifyDataSetChanged(); // Notificar que los datos han cambiado
    }



    public void Tcompletadas(View view){
        Intent comp = new Intent(this, Completadas.class);
        comp.putStringArrayListExtra("completadas", new ArrayList<>(completadas));
        startActivity(comp);
    }

    public List<String> getCompletadas() {
        return completadas;
    }

}