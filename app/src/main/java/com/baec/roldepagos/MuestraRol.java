package com.baec.roldepagos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MuestraRol extends AppCompatActivity {

    TextView txtNombreCompleto, txtCargo, txtSueldoFijo, txtSubsidioAnti, txtHorasExtra, txtSeguroSocial, txtTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muestra_rol);
        txtNombreCompleto = findViewById(R.id.tvNombreCompleto);
        txtCargo = findViewById(R.id.tvCargo);
        txtSueldoFijo = findViewById(R.id.tvSueldoFijo);
        txtSubsidioAnti = findViewById(R.id.tvSubsidioAnti);
        txtHorasExtra = findViewById(R.id.tvHorasExtra);
        txtSeguroSocial = findViewById(R.id.tvSeguroSocial);
        txtTotal = findViewById(R.id.tvTotal);
        Button btnregresar = findViewById(R.id.btregresa);

        Bundle datosEnviados = this.getIntent().getExtras();
        String nombreCompleto = datosEnviados.getString("nombreC");
        String cargo = datosEnviados.getString("cargo");
        Integer anios = datosEnviados.getInt("nanios");
        Integer hijos = datosEnviados.getInt("nHijos");
        Integer nhoras = datosEnviados.getInt("nhoras");

        double sueldoFijo = this.calculaSueldo(cargo);
        double subsidioAnti = this.calculaSubsidioAntiguedad(this.calculaSueldo(cargo),anios,hijos);
        double horasExtra = this.calculaHorasExtras(this.calculaSueldo(cargo),nhoras);
        double seguroSocial = this.calculaSeguroSocial(this.calculaSueldo(cargo));

        txtNombreCompleto.setText(nombreCompleto);
        txtCargo.setText(cargo);
        txtSueldoFijo.setText("$"+sueldoFijo);
        txtSubsidioAnti.setText("$"+subsidioAnti);
        txtHorasExtra.setText("$"+horasExtra);
        txtSeguroSocial.setText("$"+seguroSocial);
        double total = (sueldoFijo + subsidioAnti + horasExtra) - seguroSocial;
        txtTotal.setText("$"+total);

        btnregresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MuestraRol.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    public double calculaSeguroSocial(int sueldo){
        double montoSeguroSocial = 0.0891 * sueldo;
        return montoSeguroSocial;
    }
    public double calculaHorasExtras(int sueldo, int horas){
        double montoPorHorasExtras = (1.0 / 8) * sueldo * horas;
        return montoPorHorasExtras;
    }
    public double calculaSubsidioAntiguedad(int sueldo, int hijos, int antiguedad){
        int sueldoBasico = 420;
        double subsidioPorHijo = 0.02 * sueldoBasico * hijos;
        double subsidioPorAntiguedad = 0.08 * sueldo * antiguedad;
        double subsidioTotal = subsidioPorHijo + subsidioPorAntiguedad;

        return subsidioTotal;
    }
    public int calculaSueldo(String cargo){
        if (cargo.equalsIgnoreCase("PROGRAMADOR JUNIOR")){
            int sueldo = 680;
            return sueldo;
        }
        if(cargo.equalsIgnoreCase("PROGRAMADOR SEMI-SENIOR")){
            int sueldo = 980;
            return sueldo;
        }
        if (cargo.equalsIgnoreCase("PROGRAMADOR SENIOR")){
            int sueldo = 1200;
            return sueldo;
        }
        return 0;
    }
}