package com.mateo.Fuentes686Chumbita782;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.mateo.Fuentes686Chumbita782.db.DbContactos;

public class CrearContactoActivity extends AppCompatActivity {

    //private ArrayList<Contacto> listaContactos = new ArrayList<>();
    private String genero;
    private String color;

    private EditText et_nombre;
    private EditText et_apellido;
    private EditText et_numero;
    private EditText et_direccion;
    private RadioButton rb_fem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_contacto);

        et_nombre = findViewById(R.id.et_nombre);
        et_apellido = findViewById(R.id.et_apellido);
        et_numero = findViewById(R.id.et_telefono);
        et_direccion = findViewById(R.id.et_direccion);
        rb_fem = findViewById(R.id.rb_fem);
        RadioButton rb_masc = findViewById(R.id.rb_masc);
        RadioGroup rg_genero = findViewById(R.id.rg_genero);
        ImageView btn_guardar = findViewById(R.id.btn_guardar);


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
            public void onClick(View view){
                DbContactos dbContactos = new DbContactos(CrearContactoActivity.this);
                long id = dbContactos.insertarContacto(et_nombre.getText().toString(), et_apellido.getText().toString(), et_numero.getText().toString(), et_direccion.getText().toString(), genero, color);


                if (rb_fem.isChecked()) {
                    genero = "Femenino";
                    color = "#ffb3ba";
                } else if (rb_masc.isChecked()) {
                    genero = "Masculino";
                    color = "#3B96C3";
                } else {
                    Toast.makeText(CrearContactoActivity.this, "Seleccione un género por favor", Toast.LENGTH_SHORT).show();
                    return;
                }


                if(id > 0){
                    Toast.makeText(CrearContactoActivity.this, "Contacto creado", Toast.LENGTH_SHORT).show();
                    limpiar();  // Llamar a limpiar después de crear el contacto.
                    Intent intent = new Intent(CrearContactoActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(CrearContactoActivity.this, "Error al guardar Registro", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void limpiar(){
        et_nombre.setText("");
        et_apellido.setText("");
        et_numero.setText("");
        et_direccion.setText("");
        rb_fem.setChecked(false);
    }

    public void volver_Main(View v){
        Intent main_intent = new Intent(this, MainActivity.class);
        startActivity(main_intent);
        finish();
    }
}
