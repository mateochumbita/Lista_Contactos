package com.mateo.Fuentes686Chumbita782;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private Prefs prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prefs = new Prefs(this);

        // Verificar si el usuario ya está autenticado
        if (prefs.isLoggedIn()) {
            // El usuario ya está autenticado, redirigir a la actividad principal
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else {
            // Mostrar la pantalla de inicio de sesión
            setContentView(R.layout.activity_login);

            Button btn_login = findViewById(R.id.btn_login);
            btn_login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    login(v);
                }
            });
        }
    }

    public void login(View v) {
        EditText usuario = findViewById(R.id.et_user);
        EditText pass = findViewById(R.id.et_password);

        String email = usuario.getText().toString();
        String password = pass.getText().toString();

        if (email.equals("admin@gmail.com") && password.equals("carlitos123")) {
            prefs.setLoggedIn(true);

            Intent homeIntent = new Intent(this, MainActivity.class);
            startActivity(homeIntent);
            finish();
        } else {
            Toast.makeText(LoginActivity.this, "Credenciales Inválidas", Toast.LENGTH_SHORT).show();
        }
    }
}
