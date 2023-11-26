package com.example.listmaker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class Completadas extends AppCompatActivity {

    private List<String>completadas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completadas);
    }


    public void agregarALista(String ag){
        completadas.add(ag);
    }

}