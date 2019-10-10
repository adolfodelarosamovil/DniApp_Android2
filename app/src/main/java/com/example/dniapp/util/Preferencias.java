package com.example.dniapp.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 *
 * Clase para alamacenar las prefrencias del usuario en XML
 *
 * @author VMG
 * @since 1.0
 * @see com.example.dniapp.actividades.MainActivity
 */
public class Preferencias {



    public static final String FICHERO_ULTIMO = "ultimo_dni";
    public static final String CLAVE_ULTIMO_DNI = "ultimo";

    public static final String CLAVE_ID_RADIO = "idradio";


    /**
     * Este método graba en el fichero de preferencias el último dni
     * introducido por el usuario
     *
     * @param context el contexto de la aplicación
     * @param dni el valor a guardar del dni
     */
    public static void guardarDNI (Context context, String dni)
    {
        SharedPreferences sp = context.getSharedPreferences(FICHERO_ULTIMO, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(CLAVE_ULTIMO_DNI, dni);
        editor.commit();

    }

    /**
     * Obtiene el valor del último dni alamcenado en las preferencias
     *
     * @param context el contexto de la aplicación
     * @return el ultimo dni almacenado y cadena vacía en caso de que no exista
     */
    public static String obtenerUltimoDNI (Context context)
    {
        String ultimo_dni = null;

            SharedPreferences sp = context.getSharedPreferences(FICHERO_ULTIMO, Context.MODE_PRIVATE);
            ultimo_dni = sp.getString(CLAVE_ULTIMO_DNI, "");

        return  ultimo_dni;
    }


    public static void guardarRadioActivo (Context context, int id_radio)
    {
        SharedPreferences sp = context.getSharedPreferences(FICHERO_ULTIMO, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(CLAVE_ID_RADIO, id_radio);
        editor.commit();

    }

    public static int obtenerRadioActivo (Context context)
    {
        int idradio = 0;

            SharedPreferences sp = context.getSharedPreferences(FICHERO_ULTIMO, Context.MODE_PRIVATE);
            idradio = sp.getInt(CLAVE_ID_RADIO, 0 );

        return  idradio;
    }
}
