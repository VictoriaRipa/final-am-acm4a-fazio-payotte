package com.example.listmaker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class VistaDos extends AppCompatActivity {
    private List<String> tasks;
    private ArrayAdapter<String> taskAdapter;
    private EditText taskEditText;
    private ListView taskListView;
    private List <String> completadas = new ArrayList<>();

    private static final long DOUBLE_CLICK_TIME_DELTA = 300; // Intervalo para considerar como doble clic
    private long lastClickTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_dos);

        tasks = new ArrayList<>();
        taskAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tasks);

        taskListView = findViewById(R.id.taskListView);
        taskListView.setAdapter(taskAdapter);

        taskEditText = findViewById(R.id.taskEditText);
        Button addButton = findViewById(R.id.addButton);

        addButton.setOnClickListener(view -> addTask());



        // Esto cuando haces clic elimina la tarea
        taskListView.setOnItemClickListener((parent, view, position, id) -> {
            // Detectar doble clic (intervalo corto entre clics)
            long clickTime = System.currentTimeMillis();
            if (clickTime - lastClickTime < DOUBLE_CLICK_TIME_DELTA) {
                // Doble clic detectado, mover a completadas
                moveToCompleted(position);
            } else {
                // Primer clic, actualizar el tiempo del último clic
                lastClickTime = clickTime;
            }
        });
    }

    private void addTask() {
        String taskText = taskEditText.getText().toString();

        if (!taskText.isEmpty()) {
            // Agrega la tarea
            taskAdapter.add(taskText);

            // Borra el texto
            taskEditText.setText("");
        }
    }

    private void moveToCompleted(int position) {
        String tarea = taskAdapter.getItem(position);

        // Agregar la tarea a la lista completada
        completadas.add(tarea);

        // Eliminar la tarea de la lista actual
        taskAdapter.remove(tarea);

        // Notificar al adaptador que los datos han cambiado
        taskAdapter.notifyDataSetChanged();

        // Mostrar un mensaje
        Toast.makeText(this, "Tarea movida a Completadas", Toast.LENGTH_SHORT).show();
    }

    public void Tcompletadas(View view) {
        Intent intent = new Intent(this,Completadas.class);
        intent.putStringArrayListExtra("completadas", (ArrayList<String>) completadas);
        startActivity(intent);
    }

    public List<String> getCompletadas() {
        return completadas;
    }
}
