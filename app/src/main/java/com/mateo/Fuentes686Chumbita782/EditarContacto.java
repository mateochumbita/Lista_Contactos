package com.mateo.Fuentes686Chumbita782;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.mateo.Fuentes686Chumbita782.clases.Contacto;
import com.mateo.Fuentes686Chumbita782.db.DbContactos;

public class EditarContacto extends AppCompatActivity {

    //private ArrayList<Contacto> listaContactos = new ArrayList<>();
    private String genero;
    private String color;

    private EditText et_nombre;
    private EditText et_apellido;
    private EditText et_numero;
    private EditText et_direccion;
    private RadioButton rb_fem;

    Contacto contacto;
    boolean correcto = false;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_contacto);

        et_nombre = findViewById(R.id.et_nombre);
        et_apellido = findViewById(R.id.et_apellido);
        et_numero = findViewById(R.id.et_telefono);
        et_direccion = findViewById(R.id.et_direccion);
        rb_fem = findViewById(R.id.rb_fem);
        RadioButton rb_masc = findViewById(R.id.rb_masc);
        RadioGroup rg_genero = findViewById(R.id.rg_genero);
        ImageView btn_guardar = findViewById(R.id.btn_guardar);
        ImageView btn_volver = findViewById(R.id.btn_volver);


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

        DbContactos db = new DbContactos(EditarContacto.this);
        contacto = db.verContacto(id);


        if(contacto != null){
            et_nombre.setText(contacto.getNombre());
            et_apellido.setText(contacto.getApellido());
            et_numero.setText(contacto.getNumero());
            et_direccion.setText(contacto.getDireccion());
            if(contacto.getGenero().equals("Femenino")){
                rb_fem.setChecked(true);
                genero = "Femenino";
                color = "#ffb3ba";
            }else{
                rb_masc.setChecked(true);
                genero = "Masculino";
                color = "#3B96C3";
            }


        }


        rg_genero.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_fem) {
                    genero = "Femenino";
                    color = "#ffb3ba";
                } else if (checkedId == R.id.rb_masc) {
                    genero = "Masculino";
                    color = "#3B96C3";
                }
            }
        });

        btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!et_nombre.getText().equals("") && !et_apellido.getText().equals("") && !et_numero.getText().equals("") && !et_direccion.getText().equals("") && genero != null){
                    correcto =  db.EditarContacto(id, et_nombre.getText().toString(), et_apellido.getText().toString(), et_numero.getText().toString(), et_direccion.getText().toString(), genero, color);
                    if(correcto){
                        Toast.makeText(EditarContacto.this, "Contacto Modificado", Toast.LENGTH_SHORT).show();
                        verRegistro();

                    }else{
                        Toast.makeText(EditarContacto.this, "Error al Modificar el contacto", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(EditarContacto.this, "Por favor complete todos los campos y seleccione el género", Toast.LENGTH_SHORT).show();
                }
            }

        });


        btn_volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verRegistro();
            }

        });
    }



   public void verRegistro(){
        Intent intent = new Intent(this, VerContacto.class);
        intent.putExtra("ID", id);
        startActivity(intent);
   }
}