package com.example.dniapp.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.dniapp.R;
import com.example.dniapp.beans.Dni;
import com.example.dniapp.beans.DniX;
import com.example.dniapp.beans.DniY;
import com.example.dniapp.beans.DniZ;
import com.example.dniapp.util.Preferencias;

public class MainActivity extends AppCompatActivity {

    public static final String TAG_APP = "DNI_APP";
    private RadioButton radioButtonSeleccionado;
    private EditText caja_dni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.caja_dni = findViewById(R.id.dni);
        this.radioButtonSeleccionado = findViewById(R.id.radio1);

        String ultimo_dni = Preferencias.obtenerUltimoDNI(this);
        this.caja_dni.setText(ultimo_dni);



    }


    private Dni crearObjetoDni (int num_dni)
    {
        Dni dni = null;

        switch (this.radioButtonSeleccionado.getId())
        {
            case R.id.radio1: dni = new Dni(num_dni);//nacional
                break;
            case R.id.radio2: dni = new DniX(num_dni);//X
                break;
            case R.id.radio3: dni = new DniY(num_dni);//Y
                break;
            case R.id.radio4: dni = new DniZ(num_dni);//Z
                break;
        }

        return dni;
    }

    public void calcularLetraDni(View view) {
        Log.d(TAG_APP, "Ha tocado el botón de calcular");
        //TODO
        //1 obtener el número de dni introducido por el usario
        EditText caja_dni = findViewById(R.id.dni);
        String sdni = caja_dni.getText().toString();
        //2 Casting de String a Int //NO ESTOY VALIDANDO LA ENTRADA
        int num_dni = Integer.parseInt(sdni);
        //2.1 Creo el objeto DNI nuevo
        Dni dni = crearObjetoDni(num_dni);
        //calcular letra
        char letra_dni = dni.calculaLetra();
        Log.d(TAG_APP, "Letra calculada = "+letra_dni);
        //4 Lanzo la actividad del resultado pasándole la letra
        Intent intent = new Intent(this, AnimacionLetraActivity.class);
        intent.putExtra("LETRA", letra_dni);
        startActivity(intent);
    }


    //este método se invoca cuando el usuario pulsa el  botón de hacia atrás
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.d(TAG_APP, "El usuario le ha dado para atrás");
        String contenido_caja = this.caja_dni.getText().toString();
        Preferencias.guardarDNI(this, contenido_caja);

    }

    public void tocadoRadio(View view) {
        Log.d(TAG_APP, "Tocó RadioButton");
        this.radioButtonSeleccionado = (RadioButton)view;
    }

    /*private char calcularLetra (int numero_dni)
    {
        char letra_calculada =  ' ';
        int resto = -1;

            resto = (numero_dni%SECUENCIA_LETRAS_DNI.length());
            letra_calculada = SECUENCIA_LETRAS_DNI.charAt(resto);

        return letra_calculada;
    }*/
}
