package com.example.appcrypto.controlador;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.AsyncTask;
        import android.os.Bundle;
        import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
        import android.widget.ListView;
        import android.widget.Toast;


import com.example.appcrypto.R;
import com.example.appcrypto.adapter.RecyclerAdapter;
import com.example.appcrypto.io.HttpConnectCrypto;
import com.example.appcrypto.modelos.Cryptos;

import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.util.ArrayList;


public class SegundaActivity extends AppCompatActivity {


    private ArrayList<Cryptos> CryptoList = new ArrayList<Cryptos>();
    private RecyclerView recyclerView;
    private RecyclerAdapter recAdapter;
   // private ArrayAdapter<Cryptos> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);


        //Se inicializan los objetos recyclerView y recyclerAdapter(Ver punto 2), pasandole
        // a este último la lista (3.1)
        recyclerView = (RecyclerView) findViewById(R.id.recView);
        recAdapter = new RecyclerAdapter(CryptoList,getApplicationContext());
        recAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Toast.makeText(getApplicationContext(), "Selección: "+CryptoList.get(recyclerView.getChildAdapterPosition(view)).getNombre(), Toast.LENGTH_SHORT).show();
                Cryptos cryptos=CryptoList.get(recyclerView.getChildAdapterPosition(view));
                Intent i =new Intent(getApplicationContext(),TerceraActivity.class);
                i.putExtra("getDescripcion",cryptos.getDescripcion());
                i.putExtra("getIcon",cryptos.getIcon());
                i.putExtra("getNombre",cryptos.getNombre());
                i.putExtra("getMc",cryptos.getMc());
                i.putExtra("getPrecio",cryptos.getPrecio());
                i.putExtra("getSupply",cryptos.getSupply());
                i.putExtra("getWeb",cryptos.getWeb());


                startActivity(i);
            }

        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        //Por último solo debemos añadir los elementos creados anteriormente a la vista
        // padre (RecyclerView) con sus respectivos métodos.
        recyclerView.setAdapter(recAdapter);
        recyclerView.setLayoutManager(layoutManager);


        //adapter = new ArrayAdapter<Cryptos>(this, android.R.layout.simple_list_item_1, CryptoList);


        new taskConnections().execute( "/coins?skip=0&limit=40&currency=EUR");
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);return true;

    }
    public boolean onOptionsItemSelected(MenuItem items){
        int id = items.getItemId();

        if(id == R.id.item_apply){

            Intent i =new Intent(getApplicationContext(),Preferencias.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(items);
    }




    private class taskConnections extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String result = null;
                   result = HttpConnectCrypto.getRequest(strings[0]);
           //Log.d("zzz", "DATOS: " + result);
            return result;
        }

        @Override
        protected void onPostExecute(String s) {

            try {
                if (s != null) {
                    Log.d("D", "DATOS: " + s);

                    // La respuesta que nos devuelve es un texto en formato JSON. Para ello,
                    // en este caso, haremos uso de las clases que nos proporciona Android.
                    // se deberá consultar la documentación para conocer el formato de
                    // la respuesta del servidor, y así saber cómo deserializar el mensaje.
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray jsonArray = jsonObject.getJSONArray("coins");

                    String nombre = "";
                    String icon="";
                    String descripcion="";
                    String mc="";
                    String supply="";
                    String precio="";
                    String web="";


                    for (int i = 0; i < jsonArray.length(); i++) {
                        nombre = jsonArray.getJSONObject(i).getString("id");
                        icon = jsonArray.getJSONObject(i).getString("icon");
                        descripcion = jsonArray.getJSONObject(i).getString("symbol");
                        precio = jsonArray.getJSONObject(i).getString("price");
                        mc = jsonArray.getJSONObject(i).getString("marketCap");
                        supply = jsonArray.getJSONObject(i).getString("totalSupply");
                        web = jsonArray.getJSONObject(i).getString("websiteUrl");


                        CryptoList.add(new Cryptos(nombre,icon,descripcion,mc,supply,precio,web));
                        Log.d("D", "DATOS:ee " + s);
                    }
                    // Una vez tenemos los datos en nuestra colección debemos avisar al
                    // adaptador que la información ha cambiado.
                    recAdapter.notifyDataSetChanged();


                    Log.d("D", "Array: " + CryptoList.toString());



                } else {
                    Toast.makeText(SegundaActivity.this, "Problema al cargar los datos", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}

