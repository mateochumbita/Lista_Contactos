package com.mateo.Fuentes686Chumbita782;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.mateo.Fuentes686Chumbita782.adaptadores.ListaContactosAdapter;
import com.mateo.Fuentes686Chumbita782.clases.Contacto;
import com.mateo.Fuentes686Chumbita782.db.DbContactos;
import com.mateo.Fuentes686Chumbita782.db.DbHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private RecyclerView recyclerView;

    private List<Contacto> listaContactos;
    private Prefs prefs;
    ArrayList<Contacto> listaArrayContactos;
    ListaContactosAdapter adapter;
    SearchView txtBuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.listRecyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        txtBuscar = findViewById(R.id.txtBuscar);
        DbContactos dbContactos = new DbContactos(MainActivity.this);

        listaArrayContactos = new ArrayList<>();


        adapter = new ListaContactosAdapter(dbContactos.mostrarContactos());

        recyclerView.setAdapter(adapter);





        prefs = new Prefs(this); // Inicializar Prefs



        txtBuscar.setOnQueryTextListener(this);
    }

    public void logout(View v) {
        prefs.setLoggedIn(false); // Actualizar el estado de autenticación en SharedPreferences
        Intent loginIntent = new Intent(this, LoginActivity.class);
        startActivity(loginIntent);
        finish(); // Finalizar la actividad actual para que no quede en la pila
    }

    public void newContact(View v){
        Intent contactoIntent = new Intent(this, CrearContactoActivity.class);
        startActivity(contactoIntent);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.filtrado(newText);
        return false;
    }



    }

