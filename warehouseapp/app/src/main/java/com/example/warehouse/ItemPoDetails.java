package com.example.warehouse;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ItemPoDetails extends AppCompatActivity {

    TextView txtItemPoId;
    TextView txtItemName;
    TextView txtPo;
    TextView txtBatch;
    TextView txtPackaging;
    TextView txtRecDate;
    TextView txtManDate;
    TextView txtExpDate;
    TextView txtCountry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_po_details);

        txtItemPoId = findViewById(R.id.txtItemPoId);
        txtItemName = findViewById(R.id.txtItemName);
        txtPo = findViewById(R.id.txtPo);
        txtBatch = findViewById(R.id.txtBatch);
        txtPackaging = findViewById(R.id.txtPackaging);
        txtRecDate = findViewById(R.id.txtRecDate);
        txtManDate = findViewById(R.id.txtManDate);
        txtExpDate = findViewById(R.id.txtExpDate);
        txtCountry = findViewById(R.id.txtCountry);

        txtItemPoId.setText("Item PO ID : " + getIntent().getStringExtra("itemPoId"));
        txtItemName.setText("Item : " + getIntent().getStringExtra("itemName"));
        txtPo.setText("PO#" + getIntent().getStringExtra("po"));
        txtBatch.setText("Batch : " + getIntent().getStringExtra("batch"));
        txtPackaging.setText("Packaging : " + getIntent().getStringExtra("packaging"));
        txtRecDate.setText("Rec. Date : " + getIntent().getStringExtra("recDate"));
        txtManDate.setText("Man. Date : " + getIntent().getStringExtra("manDate"));
        txtExpDate.setText("Exp. Date : " + getIntent().getStringExtra("expDate"));
        txtCountry.setText("Country : " + getIntent().getStringExtra("country"));


    }
}