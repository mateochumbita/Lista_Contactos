package com.mateo.Fuentes686Chumbita782;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.mateo.Fuentes686Chumbita782.classes.Contacto;
import java.util.ArrayList;
import java.util.List;

public class CrearContactoActivity extends AppCompatActivity {

    private ArrayList<Contacto> listaContactos = new ArrayList<>();
    private String genero;
    private String color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_contacto);

        EditText et_nombre = findViewById(R.id.et_nombre);
        EditText et_apellido = findViewById(R.id.et_apellido);
        EditText et_numero = findViewById(R.id.et_telefono);
        EditText et_direccion = findViewById(R.id.et_direccion);
        RadioButton rb_fem = findViewById(R.id.rb_fem);
        RadioButton rb_masc = findViewById(R.id.rb_masc);
        RadioGroup rg_genero = findViewById(R.id.rg_genero);
        ImageView btn_guardar = findViewById(R.id.btn_guardar);

        List<Contacto> listaTemp = ContactoStorage.getListaContactos();
        if (listaTemp != null) {
            listaContactos.addAll(listaTemp);
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
                String nombre = et_nombre.getText().toString().toUpperCase();
                String apellido = et_apellido.getText().toString().toUpperCase();
                String direccion = et_direccion.getText().toString().toUpperCase();
                String numero = et_numero.getText().toString();

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

                Contacto contacto = new Contacto(nombre, apellido, numero, direccion, genero, color);
                listaContactos.add(contacto);
                ContactoStorage.setListaContactos(listaContactos);

                et_nombre.setText("");
                et_apellido.setText("");
                et_direccion.setText("");
                et_numero.setText("");
                rb_fem.setChecked(true);
                rb_masc.setChecked(true);

                Intent intent = new Intent(CrearContactoActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void volver_Main(View v){
        Intent main_intent = new Intent(this, MainActivity.class);
        startActivity(main_intent);
        finish();
    }
}
