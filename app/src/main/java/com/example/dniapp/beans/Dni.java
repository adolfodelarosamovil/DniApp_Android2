package com.example.dniapp.beans;

/**
 * Clase que representa un DNI
 */
public class Dni implements Comparable<Dni>{

    private int id;
    private int numero;
    private char letra;

    protected static final String SECUENCIA_LETRAS_DNI = "TRWAGMYFPDXBNJZSQVHLCKE";


    public Dni(int id, int numero, char letra) {
        this.id = id;
        this.numero = numero;
        this.letra = letra;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Dni(int numero) {

        this.numero = numero;
    }

    public Dni(int numero, char letra) {
        this.numero = numero;
        this.letra = letra;
    }

    public Dni(){

    }

    public int getNumero() {
        return this.numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public char getLetra() {
        return this.letra;
    }

    public void setLetra(char letra) {
        this.letra = letra;
    }

    public char calculaLetra ()
    {
        char letra_calculada =  ' ';
        int resto = -1;

            resto = (this.numero%SECUENCIA_LETRAS_DNI.length());
            letra_calculada = SECUENCIA_LETRAS_DNI.charAt(resto);

        return letra_calculada;
    }

    @Override
    public int compareTo(Dni o) {
        int resultado = 0;

        resultado = this.getNumero() - o.getNumero();

        return resultado;
    }
}
