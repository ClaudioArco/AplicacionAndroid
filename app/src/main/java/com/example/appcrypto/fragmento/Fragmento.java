package com.example.appcrypto.fragmento;



import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.example.appcrypto.R;


//Nos creamos una clase que heredara de PreferenceFragment, cuya función será 'incrustar'
// en la ventana el diseño de las preferencias a partir del xml previamente diseñado.
// ** Un Fragment, explicándolo de forma general, es una ventana dentro de otra. Android nos
// permite dividir una ventana en varias. Esto es útil por ejemplo cuando tenemos pantallas de
// grandes dimensiones, como pueden ser la de las tablets. Para ello cada parte tendrá su propio
// ciclo de vida y se manejará de forma indpendiente, cada una de estas partes son los fragments.
//

//En este caso solo tendremos un fragment dentro de la propia ventana.

public class Fragmento extends PreferenceFragmentCompat {


    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.fragmento, rootKey);
    }
}