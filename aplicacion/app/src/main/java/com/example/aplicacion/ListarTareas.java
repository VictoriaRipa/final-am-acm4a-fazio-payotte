package com.example.aplicacion;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ListarTareas extends AppCompatActivity {
    private List<String> tasks;
    private ArrayAdapter<String> taskAdapter;
    private EditText taskEditText;
    private ListView taskListView;
    private List<String> completadas = new ArrayList<>();

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
        // Configuración del botón para agregar una tarea

        addButton.setOnClickListener(view -> addTask());

        // Mover la tarea a completadas cuando se hace clic en un elemento
        taskListView.setOnItemClickListener((parent, view, position, id) -> {
            moveToCompleted(position);
        });

        // Cargar las tareas almacenadas
        loadTasksFromSharedPreferences();
    }
    // Guardar las tareas en SharedPreferences al detener la actividad

    @Override
    protected void onStop() {
        super.onStop();
        // Guardar las tareas en SharedPreferences al detener la actividad para no perder la informacion
        saveTasksToSharedPreferences();
    }
    // Método para agregar una tarea

    private void addTask() {
        String taskText = taskEditText.getText().toString();

        if (!taskText.isEmpty()) {
            // Agrega la tarea
            taskAdapter.add(taskText);

            // Borra el texto
            taskEditText.setText("");
        }
    }
    // Método para mover la tarea a la lista de completadas

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
    // Método para guardar las tareas en SharedPreferences

    private void saveTasksToSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("tasks_prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Set<String> taskSet = new HashSet<>(tasks);
        editor.putStringSet("task_list", taskSet);
        editor.apply();
    }

    private void loadTasksFromSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("tasks_prefs", MODE_PRIVATE);
        Set<String> taskSet = sharedPreferences.getStringSet("task_list", new HashSet<>());

        tasks.clear();
        tasks.addAll(taskSet);
        taskAdapter.notifyDataSetChanged();
    }

    public void Tcompletadas(View view) {
        Intent intent = new Intent(this,Completadas.class);
        intent.putStringArrayListExtra("completadas", (ArrayList<String>) completadas);
        startActivity(intent);
    }

    public void VolverAMenu(View view) {
        Intent menu = new Intent(this,Menu.class);
        startActivity(menu);
    }
}
