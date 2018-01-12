package com.jorge.menus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;


public class Salon1 extends AppCompatActivity {
    GridView listado;
    Button scan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.salon1);

        listado = (GridView) findViewById(R.id.listView);



        obtDatos();


    }

    public void obtDatos(){

        Intent intent2 = getIntent();
        Bundle extras = intent2.getExtras();
        String datos = extras.getString("val");
        int numero = 0;

        numero = Integer.parseInt(datos);

        if (extras != null) {




        }



        AsyncHttpClient client2 =new AsyncHttpClient();
        String url="https://serox.000webhostapp.com/horarios.php";
        RequestParams parametros =new RequestParams();
        parametros.put("id_salon",numero+1);

        client2.post(url, parametros, new AsyncHttpResponseHandler() {
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
            String texto,texto2,texto3,texto4,texto5,texto6;
            listado.add("Lunes");
            listado.add("Martes");
            listado.add("Miercoles");
            listado.add("Jueves");
            listado.add("Viernes");
            listado.add("Sabado");


            for (int i=0;i<jsonArray.length();i++){

                texto =jsonArray.getJSONObject(i).getString("lunes");
                texto2 =jsonArray.getJSONObject(i).getString("martes");
                texto3 =jsonArray.getJSONObject(i).getString("miercoles");
                texto4 =jsonArray.getJSONObject(i).getString("jueves");
                texto5 =jsonArray.getJSONObject(i).getString("viernes");
                texto6 =jsonArray.getJSONObject(i).getString("savado");//sabado


                listado.add(texto);
                listado.add(texto2);
                listado.add(texto3);
                listado.add(texto4);
                listado.add(texto5);
                listado.add(texto6);

            }

        }catch (Exception e){

            e.printStackTrace();
        }
        return  listado;
    }
}
