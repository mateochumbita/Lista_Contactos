package com.mateo.Fuentes686Chumbita782;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mateo.Fuentes686Chumbita782.clases.Contacto;
import com.mateo.Fuentes686Chumbita782.db.DbContactos;

public class VerContacto extends AppCompatActivity {


    EditText et_nombre, et_apellido,et_direccion, et_numero,et_genero;
    Button btnEditar;
    Contacto contacto;
    ImageView volver;
    FloatingActionButton fabEditar, fabEliminar;
    int id = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ver_contacto);

        et_nombre = findViewById(R.id.et_nombre);
        et_apellido = findViewById(R.id.et_apellido);
        et_direccion = findViewById(R.id.et_direccion);
        et_numero = findViewById(R.id.et_telefono);
        et_genero = findViewById(R.id.et_genero);
        fabEditar = findViewById(R.id.fabEditar);
        fabEliminar = findViewById(R.id.fabEliminar);
        volver = findViewById(R.id.btn_volver);

        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                id = Integer.parseInt(null);
            }else{
                id = extras.getInt("ID");
            }
        }else{
            id = (int) savedInstanceState.getSerializable("ID");
        }

        DbContactos db = new DbContactos(VerContacto.this);
        contacto = db.verContacto(id);


        if(contacto != null){
            et_nombre.setText(contacto.getNombre());
            et_apellido.setText(contacto.getApellido());
            et_numero.setText(contacto.getNumero());
            et_direccion.setText(contacto.getDireccion());
            et_genero.setText(contacto.getGenero());
            et_nombre.setInputType(InputType.TYPE_NULL);
            et_apellido.setInputType(InputType.TYPE_NULL);
            et_direccion.setInputType(InputType.TYPE_NULL);
            et_numero.setInputType(InputType.TYPE_NULL);
            et_genero.setInputType(InputType.TYPE_NULL);
        }

        fabEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent edit_intent = new Intent(VerContacto.this, EditarContacto.class);
                edit_intent.putExtra("ID", id); // Pasar el ID del contacto a editar como un extra
                startActivity(edit_intent);
            }
        });

        fabEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(VerContacto.this);
                    builder.setMessage("¿Desea eliminar el contacto?")
                            .setPositiveButton("Si",new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int i) {
                                   if( db.EliminarContacto(id)){
                                       Toast.makeText(VerContacto.this, "Contacto Eliminado", Toast.LENGTH_SHORT).show();
                                       volver_Main();
                                   }

                                }

                            })
                            .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int i) {

                                }
                            }).show();

            }
        });



        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main_intent = new Intent(VerContacto.this, MainActivity.class);
                startActivity(main_intent);
            }
        });

    }



    public void volver_Main(){
        Intent main_intent = new Intent(this, MainActivity.class);
        startActivity(main_intent);
        finish();
    }


/*
    public void editarContacto(View v){
        Intent edit_intent = new Intent(this, EditarContacto.class);
        edit_intent.putExtra("ID", id); // Pasar el ID del contacto a editar como un extra
        startActivity(edit_intent);
    }
*/
}