package com.example.tareasapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> tareas_al;
    private ArrayAdapter<String> tareas_aa;
    private ListView lv_tareas;
    private Button btn_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv_tareas = findViewById(R.id.lv_tareas);
        btn_add = findViewById(R.id.btn_add);

        btn_add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                agregar(view);
            }
        });

        tareas_al = new ArrayList<>();
        tareas_aa = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tareas_al);

        lv_tareas.setAdapter(tareas_aa);
        setUpListViewListener();

    }

    private void setUpListViewListener(){
        lv_tareas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l){
                Context context = getApplicationContext();
                Toast.makeText(context, "Tarea eliminada", Toast.LENGTH_LONG).show();

                tareas_al.remove(i);
                tareas_aa.notifyDataSetChanged();
                return true;

            }
        });
    }

    private void agregar(View view){
        EditText et_entrada = findViewById(R.id.txt_tarea);
        String tarea_str = et_entrada.getText().toString();
        if(!(tarea_str.equals(""))){
            tareas_aa.add(tarea_str);
            et_entrada.setText("");
        }else{
            Toast.makeText(getApplicationContext(), "Introduce una tarea", Toast.LENGTH_LONG).show();
        }
    }
}