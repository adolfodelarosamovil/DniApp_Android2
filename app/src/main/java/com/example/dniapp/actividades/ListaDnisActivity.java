package com.example.dniapp.actividades;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.dniapp.R;
import com.example.dniapp.adapter.DniAdapter;
import com.example.dniapp.beans.Dni;
import com.example.dniapp.dao.BaseDatosDni;
import com.example.dniapp.util.Comparador;
import com.example.dniapp.util.Preferencias;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ListaDnisActivity extends AppCompatActivity {

    List<Dni> dniList;
    DniAdapter dniAdapter;
    BaseDatosDni baseDatosDni;
    private ColorDrawable color_fondo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_dnis);

        //dniList = Preferencias.cargarFicheroDni(this);


        //creo el objeto de la base de datos
        this.baseDatosDni = new BaseDatosDni(this, "MiDB", null, 1);


        dniList = baseDatosDni.buscarDnis();

        RecyclerView recyclerView = null;
        if (dniList!=null && dniList.size() > 0)
        {
            findViewById(R.id.caja_no_resultado).setVisibility(View.GONE);
            dniAdapter = new DniAdapter(dniList);
            recyclerView = findViewById(R.id.recycler_view);
            recyclerView.setAdapter(dniAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        }

        color_fondo = new ColorDrawable(Color.YELLOW);

        //Swipe
        ItemTouchHelper.SimpleCallback callback =
                new ItemTouchHelper.SimpleCallback(ItemTouchHelper.DOWN, ItemTouchHelper.LEFT){  //DOWN , LEFT|RIGTH
                    @Override //Metodo invocado al desplazamiento vertical del Item
                    public boolean onMove(@NonNull RecyclerView recyclerView,
                                          @NonNull RecyclerView.ViewHolder viewHolder,
                                          @NonNull RecyclerView.ViewHolder target) {


                        // Swipe
                        Dni dniOrigen = dniList.get(viewHolder.getAdapterPosition());
                        Dni dniTarget = dniList.get(target.getAdapterPosition());

                        int posicionOrigen = viewHolder.getAdapterPosition();
                        int posicionTarget = target.getAdapterPosition();

                        //dniList.set(posicionOrigen, dniTarget);
                        //dniList.set(posicionTarget, dniOrigen);

                        Collections.swap(dniList, posicionOrigen, posicionTarget);

                        dniAdapter.notifyDataSetChanged();


                        return true;


                        //return false;

                    }

                    @Override //Metodo invocado al desplazamiento lateral del Item
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                        Log.d("MIAPP","direction: " + direction);
                        int posicion = viewHolder.getAdapterPosition();
                        Log.d("MIAPP", "Posicion: " + posicion);
                        //ListaDnisActivity.this.dniList

                        //Recupera DNI antes de eliminarlo
                        Dni dni = dniList.get(posicion);
                        //Borra de la BD
                        baseDatosDni.borrarDni(dni);

                        //Borra de la lista
                        dniList.remove(posicion);


                        if (dniList==null || dniList.size() <= 0)
                        {
                            recreate();
                        }else{
                            dniAdapter.notifyDataSetChanged();
                        }




                    }

                    @Override //Metodo invocado cada vez que se mueve un poquito vertical u horizontal del Item
                    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                        Log.d("MIAPP", "dX " + dX);
                        Log.d("MIAPP", "dY " + dY);
                        Log.d("MIAPP", "actionState " + actionState);
                        Log.d("MIAPP", "isCurrentlyActive " + isCurrentlyActive);

                        color_fondo.setBounds(viewHolder.itemView.getRight() + (int)dX, viewHolder.itemView.getTop(), viewHolder.itemView.getRight(), viewHolder.itemView.getBottom());

                        color_fondo.draw(c);

                        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);



                    }
                };

        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerView);



    }

    public void ordenarPorNumero(View view) {
        Log.d("MIAPP", "EN ordenarPorNumero");
        Collections.sort(dniList);
        dniAdapter.setDniList(dniList);
        dniAdapter.notifyDataSetChanged();
        Toast toast = Toast.makeText(this, "Lista ordenada por número", Toast.LENGTH_LONG);
        toast.show();

    }

    public void ordenarPorLetra(View view) {
        Log.d("MIAPP", "EN ordenarPorLetra");
        Collections.sort(dniList,new Comparador());
        dniAdapter.setDniList(dniList);
        dniAdapter.notifyDataSetChanged();

        Toast toast = Toast.makeText(this, "Lista ordenada por letra", Toast.LENGTH_LONG);
        toast.show();
    }
}
