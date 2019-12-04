package com.example.mysupermarket;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class calculation extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.pidButton)
    Button mPidBtn;

    @BindView(R.id.pidEdit)
    EditText mPidEdit;

    @BindView(R.id.pidText)
    TextView mPidText;

    private ProgressDialog mProgressDialog;

    private String url = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculation);
        ButterKnife.bind(this);

        mPidBtn.setOnClickListener(this);

        AutheticationDialog();

        Intent newIntent = getIntent();
        String result = newIntent.getStringExtra("result");
        mPidEdit.setText(result);


    }

    private void AutheticationDialog() {

        mProgressDialog = new ProgressDialog(this);

        mProgressDialog.setTitle("Loading .....");
        mProgressDialog.setMessage("Fetching Product Detail");
        mProgressDialog.setCancelable(false);
    }

    @Override
    public void onClick(View v) {

        showProduct();

    }

    private void showProduct() {

        mProgressDialog.show();

        final  String result = mPidEdit.getText().toString().trim();



    }
}
