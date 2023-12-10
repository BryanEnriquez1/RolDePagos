package com.baec.roldepagos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText nombresCom = findViewById(R.id.edNombre);
        EditText anios = findViewById(R.id.edAnios);
        EditText hijos = findViewById(R.id.edHijos);
        EditText horas = findViewById(R.id.edHoras);
        Button btncalcular = findViewById(R.id.btnCalculaSaldo);

        Spinner cargos = (Spinner) findViewById(R.id.cbxCargos);
        String [] opCargos = {
                "GERENTE",
                "EMPLEADO",
                "CAJERO",
                "ATENCION AL CLIENTE"
        };
        ArrayAdapter <String> CargosEmpresa = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opCargos);
        cargos.setAdapter(CargosEmpresa);

        btncalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MuestraRol.class);
                Bundle datos = new Bundle();
                String nombreCompleto = nombresCom.getText().toString();
                Integer anio = Integer.parseInt(anios.getText().toString());
                Integer nHijos = Integer.parseInt(hijos.getText().toString());
                Integer nHoras = Integer.parseInt(horas.getText().toString());
                String cargo = cargos.getSelectedItem().toString();
                datos.putString("nombreC",nombreCompleto);
                datos.putInt("anios",anio);
                datos.putInt("hijos",nHijos);
                datos.putInt("horas",nHoras);
                datos.putString("cargo",cargo);
                intent.putExtras(datos);
                startActivity(intent);
            }
        });
    }
}