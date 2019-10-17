package com.example.dniapp.dao;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.dniapp.beans.Dni;

import java.util.ArrayList;
import java.util.List;



public class BaseDatosDni extends SQLiteOpenHelper {


    private final String sqlCreacionTablaDni = "CREATE TABLE DNI (id INTEGER PRIMARY KEY AUTOINCREMENT, numero INTEGER, letra TEXT)";


    public BaseDatosDni(Context contexto, String nombre, SQLiteDatabase.CursorFactory factory, int version) {
        super(contexto, nombre, factory, version); //el método padre, llamará a Oncreate o OnUpgrade, segn corresponda
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(sqlCreacionTablaDni);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

        //En caso de que al constructor le pasemos un número de versión distinto a
        // la base de datos existente, este métdo es invocado. Esto sería necesario
        //cuando modificamos la estrucutura de la base de datos

        //Aquí, deberíamos
        // 1 - Extraer los datos de la vieja versión y copiarlos a la nueva instancia
        // 2 - Crear la nueva versión
        // 3 - Cargar los datos en las tablas de la nueva versión

    }

    private void cerrarBaseDatos (SQLiteDatabase database)
    {
        database.close();
    }

    public void insertarDni (Dni dni) {


        SQLiteDatabase database = null;
        try {

            database = this.getWritableDatabase();
            database.execSQL("INSERT INTO DNI (numero, letra) VALUES (" + dni.getNumero() + " , '" + dni.getLetra() + "')");


        } catch (NullPointerException e) {
            Log.d("MIAPP", "Ha petao insertando un DNI", e);
        } catch (Exception e) {
            Log.d("MIAPP", "Ha petao insertando un DNI" + dni.toString(), e);
        } finally {
            this.cerrarBaseDatos(database);
        }

    }

    public List<Dni> buscarDnis ()
    {
        List<Dni> lista_dni = null;
        Dni dni = null;
        int aux_id = -1;
        String numero_aux = null;
        char letra_aux;


        String consulta = "SELECT id, numero, letra FROM DNI";


        SQLiteDatabase basedatos = this.getReadableDatabase();
        Cursor cursor = basedatos.rawQuery(consulta, null);


        if( cursor != null && cursor.getCount() >0)
        {
            cursor.moveToFirst();
            lista_dni = new ArrayList<Dni>(cursor.getCount());

            do
            {
              aux_id = cursor.getInt(0); //la posicion primera, el id
              numero_aux = cursor.getString(1); //la posicion segunda, el id
              int mumero_aux_int = Integer.parseInt(numero_aux);
              letra_aux =  cursor.getString(2).charAt(0);
              dni = new Dni(aux_id, mumero_aux_int, letra_aux);
              lista_dni.add(dni);
            }while (cursor.moveToNext());

            cursor.close();
        }

        this.cerrarBaseDatos(basedatos);

        return lista_dni;
    }



    public Dni buscarDni (String numero)
    {
        Dni dni = null;
        int aux_id = -1;
        String numero_aux = null;
        char letra_aux;


        String consulta = "SELECT id, numero, letra FROM DNI WHERE numero LIKE %"+numero+"%;";


        SQLiteDatabase basedatos = this.getReadableDatabase();
        Cursor cursor = basedatos.rawQuery(consulta, null);


        if( cursor != null && cursor.getCount() >0)
        {
            cursor.moveToFirst();

            aux_id = cursor.getInt(0); //la posicion primera, el id
            numero_aux = cursor.getString(1); //la posicion segunda, el id
            int mumero_aux_int = Integer.parseInt(numero_aux);
            letra_aux = cursor.getString(2).charAt(0);
            dni = new Dni(aux_id, mumero_aux_int, letra_aux);

            cursor.close();
        }

        this.cerrarBaseDatos(basedatos);

        return dni;
    }

}
