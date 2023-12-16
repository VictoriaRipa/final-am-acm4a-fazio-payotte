package com.example.aplicacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void menu(View view){
        Intent menu =new Intent(this,Login.class);
        startActivity(menu);
    }

    public void registrarse(View view){
        Intent reg =new Intent(this,Registro.class);
        startActivity(reg);
    }

}