package com.jorge.menus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.GridView;


import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class Bloque1 extends AppCompatActivity {
    GridView listado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bloque1);

        listado =(GridView) findViewById(R.id.listView);
        listado.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(Bloque1.this,""+i,Toast.LENGTH_SHORT).show();
                Intent intent2 =new Intent(Bloque1.this,Salon1.class);

                int numero = i;

                String cadena = "";
                cadena = String.valueOf(numero);

                intent2.putExtra("val",cadena);
                startActivity(intent2);



            }

        });
        obtDatos();


    }
    public void obtDatos(){
        AsyncHttpClient client =new AsyncHttpClient();
        String url="https://serox.000webhostapp.com/salon.php";
        RequestParams parametros =new RequestParams();
        //parametros.put("id_salon",25);

        client.post(url, parametros, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(statusCode==200){
                    cargarListaJSON(obtDatos(new String(responseBody)));
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });


    }

    public  void cargarListaJSON(ArrayList<String> datos){

        ArrayAdapter<String> adapter =new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,datos);
        listado.setAdapter(adapter);

    }

    public ArrayList<String> obtDatos (String response){
        ArrayList<String> listado=new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(response);
            String texto,texto2,texto3;

            for (int i=0;i<21;i++){

                texto =jsonArray.getJSONObject(i).getString("num");

                listado.add(texto);

            }

        }catch (Exception e){

            e.printStackTrace();
        }
        return  listado;
    }
}
