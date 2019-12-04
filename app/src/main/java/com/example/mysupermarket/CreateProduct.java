package com.example.mysupermarket;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateProduct extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.PriceEdit)
    EditText mPriceEdit;
    @BindView(R.id.DescribeEdit) EditText mDescribeEdit;
    @BindView(R.id.ProductNameEdit) EditText mProductNameEdit;
    @BindView(R.id.EditQuanity) EditText mEditQuanity;
    @BindView(R.id.pid) EditText mPid;
    @BindView(R.id.createProduct)
    TextView mCreateProduct;

    private ProgressDialog mProgressDialog;
    String httpUrl = "http://192.168.43.248/scripts/create_product.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_product);

        createAuthProgressDialog();

        ButterKnife.bind(this);
        mCreateProduct.setOnClickListener(this);
        Intent myIntent = getIntent();

        String result = myIntent.getStringExtra("result");
        mPid.setText(result);
    }

    private void createAuthProgressDialog() {

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle("Loading .......");
        mProgressDialog.setMessage("Inserting Data to the server !");
        mProgressDialog.setCancelable(false);

    }

    @Override
    public void onClick(View v) {
        if (v == mCreateProduct){
            createProduct();
        }
    }

    private void createProduct() {

        mProgressDialog.show();

        final String price = mPriceEdit.getText().toString().trim();
        final String name = mProductNameEdit.getText().toString().trim();
        final String description = mDescribeEdit.getText().toString().trim();
        final String quantity = mEditQuanity.getText().toString().trim();
        final String pid = mPid.getText().toString().trim();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, httpUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(CreateProduct.this, response, Toast.LENGTH_SHORT).show();
                        mProgressDialog.dismiss();

                        Intent intent = new Intent(CreateProduct.this,salesActivity.class);
                        startActivity(intent);
                        finish();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CreateProduct.this , error.getMessage(),Toast.LENGTH_SHORT).show();
                mProgressDialog.dismiss();
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String ,String >();

                params.put("name" , name);
                params.put("price" ,price);
                params.put("quantity",quantity);
                params.put("description",description);
                params.put("pid",pid);

                return params;
            }
        };


        RequestQueue mRequestQue = Volley.newRequestQueue(this);
        mRequestQue.add(stringRequest);

    }
}
