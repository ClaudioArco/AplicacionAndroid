package com.example.appcrypto.controlador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.appcrypto.R;

public class TerceraActivity extends AppCompatActivity {
    private TextView nombre;
    private TextView descripcion;
    private TextView mc;
    private TextView supply;
    private TextView precio;
    private TextView icon;
    private TextView web;
    private ImageView imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tercera);
        Intent i = getIntent();
        //Bundle bundle=getIntent().getExtras();
        String getDescripcion= i.getStringExtra("getDescripcion");
        String getIcon= i.getStringExtra("getIcon");
        String getNombre= i.getStringExtra("getNombre");
        String getMc= i.getStringExtra("getMc");
        String getPrecio= i.getStringExtra("getPrecio");
        String getSupply= i.getStringExtra("getSupply");
        String getWeb= i.getStringExtra("getWeb");


        descripcion  = (TextView) findViewById(R.id.textViewSimbolo);
        nombre    = (TextView)   findViewById(R.id.textViewnombre);
        mc    = (TextView)   findViewById(R.id.textViewmc);
        supply    = (TextView)   findViewById(R.id.textViewsupply);
        precio    = (TextView)   findViewById(R.id.textViewprecio);
        web    = (TextView)   findViewById(R.id.textViewurl);
        imgView = (ImageView) findViewById(R.id.imageView);
        descripcion.setText(getDescripcion);
        nombre.setText(getNombre);
        mc.setText(getMc);
        supply.setText(getSupply);
        precio.setText(getPrecio);
        web.setText(getWeb);


        Glide.with(this)
                .load(getIcon)
                //.placeholder(progressDrawable)
                .error(R.mipmap.ic_launcher)
                .into(imgView);








    }

}