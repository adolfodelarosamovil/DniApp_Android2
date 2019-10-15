package com.example.dniapp.util;

import com.example.dniapp.beans.Dni;

import java.util.Comparator;

public class Comparador implements Comparator<Dni> {

    @Override
    public int compare(Dni o1, Dni o2) {
        int resultado = o1.getLetra() - o2.getLetra();
        return resultado;
    }
}
