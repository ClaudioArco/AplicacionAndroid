package com.example.appcrypto.controlador;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.appcrypto.R;
import com.example.appcrypto.fragmento.Fragmento;

public class Preferencias extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferencias);


        // Punto 1 en la clase SettingsFragment): Este metodo se encarga de introducir
        // el fragment ya creado en el contenedor de la vista padre (activity_setting.xml)
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.contenedor, new Fragmento());


        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


    }

    //Para que tenga efecto al pulsar debemos implementar qué debe hacer cuando el usuario
    // lo pulse.
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}