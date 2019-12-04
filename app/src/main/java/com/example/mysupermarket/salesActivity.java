package com.example.mysupermarket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;

import com.google.zxing.integration.android.IntentIntegrator;

import butterknife.BindView;
import butterknife.ButterKnife;

public class salesActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView mCardView1 , mCardView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);

        mCardView1 = (CardView) findViewById(R.id.cardView1);
        mCardView2 = (CardView) findViewById(R.id.cardView2);

        mCardView1.setOnClickListener(this);
        mCardView2.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == mCardView1){

            Intent intent = new Intent(salesActivity.this,BarcodeActivity.class);
            startActivity(intent);
        }
        if (v == mCardView2){

            Intent newIntent = new Intent(salesActivity.this,activityBarcode.class);
            startActivity(newIntent);
            finish();

        }

    }


}
