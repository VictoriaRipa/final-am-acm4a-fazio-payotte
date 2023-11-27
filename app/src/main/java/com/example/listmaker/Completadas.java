package com.example.listmaker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

        ListView completadasListView = findViewById(R.id.listViewCompletadas);
        completadasAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        completadasListView.setAdapter(completadasAdapter);

        Intent intent = getIntent();
        List<String> completadasList = intent.getStringArrayListExtra("completadas");
        if (completadasList != null) {
            completadasAdapter.addAll(completadasList);
        }
    }

    public void listarTareas(View view) {
        Intent intent = new Intent(this,VistaDos.class);
        startActivity(intent);
    }

}