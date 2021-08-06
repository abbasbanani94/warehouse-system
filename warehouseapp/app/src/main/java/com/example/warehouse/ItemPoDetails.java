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
    TextView txtLocation;

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
        txtLocation = findViewById(R.id.txtLocation);

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://192.168.8.105:8080")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        ItemPoClient client = retrofit.create(ItemPoClient.class);
        Call<ItemPoAppDto> call = client.findItemPoDetailsForApp(Integer.parseInt(getIntent().getStringExtra("itemPoId")));

        call.enqueue(new Callback<ItemPoAppDto>() {
            @Override
            public void onResponse(Call<ItemPoAppDto> call, Response<ItemPoAppDto> response) {
                ItemPoAppDto dto = response.body();
                txtItemPoId.setText("Item PO ID : " + dto.getId());
                txtItemName.setText("Item : " + dto.getItemName());
                txtPo.setText("PO#" + dto.getPurchaseOrderNo());
                txtBatch.setText("Batch : " + dto.getBatchNo());
                txtPackaging.setText("Packaging : " + dto.getPackaging());
                txtRecDate.setText("Rec. Date : " + dto.getRecDate());
                txtManDate.setText("Man. Date : " + dto.getManDate());
                txtExpDate.setText("Exp. Date : " + dto.getExpDate());
                txtCountry.setText("Country : " + dto.getCountryName());
                txtLocation.setText("Location : " + dto.getLocation());
            }

            @Override
            public void onFailure(Call<ItemPoAppDto> call, Throwable t) {
                Toast.makeText(ItemPoDetails.this,t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}