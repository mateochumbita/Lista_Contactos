package com.mateo.Fuentes686Chumbita782;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.mateo.Fuentes686Chumbita782.adapters.ContactoAdapter;
import com.mateo.Fuentes686Chumbita782.classes.Contacto;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ContactoAdapter adaptador;
    private List<Contacto> listaContactos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SearchView searchView = findViewById(R.id.searchView);
        listaContactos = ContactoStorage.getListaContactos();


        recyclerView = findViewById(R.id.listRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adaptador = new ContactoAdapter(this, listaContactos);
        recyclerView.setAdapter(adaptador);




        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filtrarContactos(newText);
                return true;
            }
        });



    }



    public void logout( View v){
        Intent login_intent = new Intent(this, LoginActivity.class);
        startActivity(login_intent);
    }

    public void New_contact(View v){
        Intent contacto_intent = new Intent(this, CrearContactoActivity.class);
        startActivity(contacto_intent);
    }



    private void filtrarContactos(String searchText) {
        List<Contacto> filteredList = new ArrayList<>();

        if (searchText.isEmpty()) {
            filteredList.addAll(listaContactos);
        } else {

            for (Contacto contact : listaContactos) {
                if (contact.getNombre().toLowerCase().startsWith(searchText.toLowerCase()) ||
                        contact.getApellido().toLowerCase().startsWith(searchText.toLowerCase())) {
                    filteredList.add(contact);
                }
            }
        }

        adaptador.filterList(filteredList);
    }

}