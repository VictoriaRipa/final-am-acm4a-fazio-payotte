package com.example.listmaker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void showToast(View view) {
        String msg = "Hello Toast!";
        Toast toast = Toast.makeText(
                this, msg, Toast.LENGTH_LONG);
        toast.show();
        TextView textView = findViewById(R.id.botonIngresar);
        textView.setText("ingresando..");
    }

}