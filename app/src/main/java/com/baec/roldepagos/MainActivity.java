package com.baec.roldepagos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

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
                "PROGRAMADOR JUNIOR",
                "PROGRAMADOR SEMI-SENIOR",
                "PROGRAMADOR SENIOR"
        };
        ArrayAdapter <String> CargosEmpresa = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opCargos);
        cargos.setAdapter(CargosEmpresa);

        btncalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MuestraRol.class);
                Bundle datos = new Bundle();
                String nombreCompleto = nombresCom.getText().toString();
                String aniosStr = anios.getText().toString();
                String hijosStr = hijos.getText().toString();
                String horasStr = horas.getText().toString();
                String cargo = cargos.getSelectedItem().toString();

                try {
                    // Intenta convertir las cadenas a números
                    Integer nanio = Integer.parseInt(aniosStr);
                    Integer nHijos = Integer.parseInt(hijosStr);
                    Integer nHoras = Integer.parseInt(horasStr);

                    // Verifica que los campos no estén vacíos
                    if (!nombreCompleto.isEmpty() && nanio != null && nHijos != null && nHoras != null && !cargo.isEmpty()) {
                        Toast.makeText(MainActivity.this, "CALCULANDO SU SALARIO", Toast.LENGTH_SHORT).show();
                        datos.putString("nombreC", nombreCompleto);
                        datos.putInt("nanios", nanio);
                        datos.putInt("nhijos", nHijos);
                        datos.putInt("nhoras", nHoras);
                        datos.putString("cargo", cargo);
                        intent.putExtras(datos);
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "INGRESE DATOS", Toast.LENGTH_SHORT).show();
                    }
                } catch (NumberFormatException e) {
                    // Maneja la excepción si ocurre un error al convertir las cadenas a números
                    Toast.makeText(MainActivity.this, "ASEGURECE DE LLENAR BIEN LOS CAMPOS", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}