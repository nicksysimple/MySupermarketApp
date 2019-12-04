package com.example.mysupermarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class activityBarcode extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode2);

        IntentIntegrator integrator = new IntentIntegrator(this);

        integrator.setPrompt("Scann Barcode");
        integrator.initiateScan();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanResult != null) {
            String result = scanResult.getContents();

            Intent newIntent = new Intent(activityBarcode.this, calculation.class);
            newIntent.putExtra("result",result);
            startActivity(newIntent);
            finish();



        } else {
            Toast.makeText(activityBarcode.this, "Error occurred", Toast.LENGTH_SHORT).show();

        }

    }
}
