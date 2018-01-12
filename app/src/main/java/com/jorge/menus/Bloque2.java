package com.jorge.menus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * Created by jorge on 30/04/17.
 */
public class Bloque2 extends AppCompatActivity {
    GridView listado;
    Button scan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bloque2);
        //scan = (Button) findViewById(R.id.scan);
        listado =(GridView) findViewById(R.id.listView);
        listado.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(Bloque2.this,""+i,Toast.LENGTH_SHORT).show();
                Intent intent2 =new Intent(Bloque2.this,Salon2.class);

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
        AsyncHttpClient client2 =new AsyncHttpClient();
        String url="https://serox.000webhostapp.com/salon2.php";
        RequestParams parametros2 =new RequestParams();
        //parametros.put("id_salon",25);

        client2.post(url, parametros2, new AsyncHttpResponseHandler() {
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

            for (int i=0;i<jsonArray.length();i++){

                texto =jsonArray.getJSONObject(i).getString("num");

                listado.add(texto);

            }

        }catch (Exception e){

            e.printStackTrace();
        }
        return  listado;
    }

}
