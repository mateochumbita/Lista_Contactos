package com.mateo.Fuentes686Chumbita782;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }


    public void login(View v){
        EditText usuario = findViewById(R.id.et_user);
        EditText pass = findViewById(R.id.et_password);
        Button btn_login = findViewById(R.id.btn_login);

        String email = usuario.getText().toString();
        String password = pass.getText().toString();


        if(email.equals("admin@gmail.com") && password.equals("carlitos123")){

            Intent home_intent = new Intent(this, MainActivity.class);
            startActivity(home_intent);
            finish();
        }else{
            Toast.makeText(LoginActivity.this, "Credenciales Inválidas", Toast.LENGTH_SHORT).show();

        }
    }
}