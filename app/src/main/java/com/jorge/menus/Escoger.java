package com.jorge.menus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;

/**
 * Created by jorge on 01/05/17.
 */
public class Escoger extends AppCompatActivity {

    Spinner escoger;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.escoger);
        escoger = (Spinner) findViewById(R.id.sEscoger);

        String[] opciones = {"Escoger", "Bloque50", "Bloque33"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opciones);

        escoger.setAdapter(adapter);

    }
    public void I(View v) {
        Intent i;
        String selec = escoger.getSelectedItem().toString();
        if (selec.equals("Bloque50")) {
            i=new Intent(this, Bloque1.class);
            startActivity(i);
        }else {
            if (selec.equals("Bloque33")) {
                i=new Intent(this, Bloque2.class);
                startActivity(i);
            }else {
                Toast.makeText(this,"Debes escoger una opcion",Toast.LENGTH_LONG).show();

            }

        }
    }
}
