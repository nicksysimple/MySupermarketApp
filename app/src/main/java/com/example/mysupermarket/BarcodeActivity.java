package com.example.mysupermarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class BarcodeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode);

        IntentIntegrator integrator = new IntentIntegrator(this);

        integrator.setPrompt("Scan Barcode");
        integrator.initiateScan();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanResult != null) {
            String result = scanResult.getContents();

            Intent myIntent = new Intent(BarcodeActivity.this, CreateProduct.class);
            myIntent.putExtra("result",result);
            startActivity(myIntent);
            finish();



        } else {
            Toast.makeText(BarcodeActivity.this, "Error occurred", Toast.LENGTH_SHORT).show();

        }

    }

}
