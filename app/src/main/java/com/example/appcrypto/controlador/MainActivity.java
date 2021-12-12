package com.example.appcrypto.controlador;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appcrypto.R;
import com.example.appcrypto.modelos.Usuarios;
import com.orm.SugarContext;
import com.orm.SugarRecord;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText mTextoemail;
    private EditText mTextopassword;
    private Button mBotonlogin;
    private String password;
    private String email;
    private Button mBotonsingup;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SugarContext.init(this);

        mTextoemail  = (EditText) findViewById(R.id.texto_email);
        mTextopassword    = (EditText)   findViewById(R.id.texto_password);
        mBotonlogin    = (Button)   findViewById(R.id.boton_login);
        mBotonsingup = (Button)   findViewById(R.id.boton_sing);



        mBotonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lanzarlogin();
            }
        });
        mBotonsingup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lanzarsing();
            }
        });


    }
    public AlertDialog createAlertDialog(String titulo, String mensaje){
        // Creamos un 'builder' o constructor que nos ayude a configurar el cuadro de dialogo
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        //  Este objeto builder nos permitirá añadir todas las configuraciones que se necesiten
        // antes de crear el alert. En este ejemplo se añade el mensaje y el titulo del alert
        builder.setMessage(mensaje)
                .setTitle(titulo);

        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });



        //  Una vez hemos añadido todas las configuraciones creamos el alertDialog. En este
        // caso, devolvemos el objeto creado.
        return builder.create();
    }

    private void lanzarlogin() {
        email=mTextoemail.getText().toString().trim();
        password=mTextopassword.getText().toString().trim();
       if(email.equals("")||password.equals("")){
            Toast.makeText(this, "Tiene que rellenar todos los campos", Toast.LENGTH_LONG).show();
        }
        else{
           List<Usuarios> usersonly= Usuarios.find(Usuarios.class,"email=? ",email);
            List<Usuarios> users= Usuarios.find(Usuarios.class,"email=? and password=?",email,password);
            if(users.isEmpty()||usersonly.isEmpty()){

                Toast.makeText(this, "Tiene que registrarse primero", Toast.LENGTH_LONG).show();

          }
            else{

                Toast.makeText(this, "acceso correcto", Toast.LENGTH_LONG).show();
                Intent i =new Intent(MainActivity.this,SegundaActivity.class);
                startActivity(i);
            }
        }
   }
    private void lanzarsing() {
        email=mTextoemail.getText().toString().trim();
        password=mTextopassword.getText().toString().trim();
        if(email.equals("")||password.equals("")){


            AlertDialog alertDialog = createAlertDialog("Campos", "Tiene que rellenar todos los campos");
            alertDialog.show();
        }
        else{
            List<Usuarios> usersonly= Usuarios.find(Usuarios.class,"email=? ",email);
            List<Usuarios> users= Usuarios.find(Usuarios.class,"email=? and password=?",email,password);
            if(users.isEmpty()||usersonly.isEmpty()){
                Toast.makeText(this, "correcto", Toast.LENGTH_LONG).show();

                Usuarios usuario=new Usuarios();
                usuario.setEmail(email);
                usuario.setPassword(password);
                usuario.save();
                mTextoemail.setText("");
                mTextopassword.setText("");
                Intent i =new Intent(MainActivity.this,SegundaActivity.class);
                startActivity(i);
            }
            else{

                Toast.makeText(this, "Error ya existe", Toast.LENGTH_LONG).show();

            }
        }
   }
}