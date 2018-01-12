package com.jorge.menus;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class ReaderActivity extends AppCompatActivity {
    private Button scan_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reader);
        scan_btn = (Button) findViewById(R.id.scan_btn);
        final Activity activity = this;
        scan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator integrator = new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("Scan");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null){
            if(result.getContents()==null){
                Toast.makeText(this, "ha   cancelado el  escaneo  ", Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(this,result.getContents(),Toast.LENGTH_LONG).show();

                String dato=result.getContents().toString();
                if (dato.equals("ing")) {
                    Intent intent2 =new Intent(ReaderActivity.this,Bloque1.class);
                    Toast.makeText(this,"correcto",Toast.LENGTH_LONG).show();
                    startActivity(intent2);

                }else  if (dato.equals("lic")) {
                    Intent intent2 =new Intent(ReaderActivity.this,Bloque2.class);
                    Toast.makeText(this,"correcto",Toast.LENGTH_LONG).show();
                    startActivity(intent2);

                }
                //startActivity(intent);
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
