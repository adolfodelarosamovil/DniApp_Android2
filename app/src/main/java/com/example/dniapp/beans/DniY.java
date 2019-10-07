package com.example.dniapp.beans;

import android.content.Intent;

/**
 * Clase que representa un DNI
 */
public class DniY extends Dni {

    public DniY(int numero) {
        super(numero);
    }

    @Override
    public char calculaLetra() {
        char letra_calculada = ' ';

            String num_str_ceros = String.format("%07d", this.getNumero());
            num_str_ceros = '1'+num_str_ceros;
            int numero_ampliado = Integer.parseInt(num_str_ceros);//133
            this.setNumero(numero_ampliado);
            letra_calculada = super.calculaLetra();

        return letra_calculada;


    }

}
