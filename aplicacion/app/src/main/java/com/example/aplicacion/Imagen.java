package com.example.aplicacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class Imagen extends AppCompatActivity {

    private ImageView imageView;
    private EditText urlEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagen);
        // Inicialización de vistas

        imageView = findViewById(R.id.imageView);
        urlEditText = findViewById(R.id.urlingresada);
    }

    public void cargarImagen(View view) {
        String imageUrl = urlEditText.getText().toString();
        if (!imageUrl.isEmpty()) {
            new ImageDownloaderTask(imageView).execute(imageUrl);
        } else {
            // Manejo si la URL está vacía
        }
    }

    public void volverAlMenu(View view){
        Intent menu =new Intent(Imagen.this,Menu.class);
        startActivity(menu);
    }


    // url de prueba:https://www.thesprucepets.com/thmb/hxWjs7evF2hP1Fb1c1HAvRi_Rw0=/2765x0/filters:no_upscale():strip_icc()/chinese-dog-breeds-4797219-hero-2a1e9c5ed2c54d00aef75b05c5db399c.jpg

}