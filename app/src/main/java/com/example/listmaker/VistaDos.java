package com.example.listmaker;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Button;
import java.util.ArrayList;
import java.util.List;

    public class VistaDos extends AppCompatActivity {
        private List<String> tasks;
        private ArrayAdapter<String> taskAdapter;
        private EditText taskEditText;
        private ListView taskListView;
        public  Completadas completadas;

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
             completadas  = new Completadas();

            // Esto cuando haces click elimina la tarea
            taskListView.setOnItemClickListener((parent, view, position, id) -> deleteTask(position));
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

        private void deleteTask(int position) {
            String completedTask = taskAdapter.getItem(position);
            // Elimina la tarea seleccionada
            taskAdapter.remove(completedTask);
            completadas.agregarALista(completedTask);

        }

        public void Tcompletadas(View view){
            Intent comp =new Intent(this,Completadas.class);
            startActivity(comp);
        }
    }

