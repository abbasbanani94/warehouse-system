package com.example.warehouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class KitPoDetails extends AppCompatActivity {

    TextView txtKitPoId;
    TextView txtKitName;
    TextView txtKitPo;
    TextView txtKitBatch;
    TextView txtKitRecDate;
    TextView txtKitManDate;
    TextView txtKitExpDate;
    TextView txtKitCountry;
    TextView txtKitLocation;
    TextView txtKitBoxes;
    TextView txtKitPallet;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kit_po_details);

        txtKitPoId = findViewById(R.id.txtKitPoId);
        txtKitName = findViewById(R.id.txtKitName);
        txtKitPo = findViewById(R.id.txtKitPo);
        txtKitBatch = findViewById(R.id.txtKitBatch);
        txtKitRecDate = findViewById(R.id.txtKitRecDate);
        txtKitManDate = findViewById(R.id.txtKitManDate);
        txtKitExpDate = findViewById(R.id.txtKitExpDate);
        txtKitCountry = findViewById(R.id.txtKitCountry);
        txtKitLocation = findViewById(R.id.txtKitLocation);
        txtKitBoxes = findViewById(R.id.txtKitBoxes);
        txtKitPallet = findViewById(R.id.txtKitsPallet);

        SharedPreferences preferences = getSharedPreferences("warehouse",MODE_PRIVATE);
        url = preferences.getString("url","");

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        KitPoClient client = retrofit.create(KitPoClient.class);
        Call<KitPoAppDto> call = client.findKitPoDetailsForApp(Integer.parseInt(getIntent().getStringExtra("kitPoId")));

        call.enqueue(new Callback<KitPoAppDto>() {
            @Override
            public void onResponse(Call<KitPoAppDto> call, Response<KitPoAppDto> response) {
                KitPoAppDto dto = response.body();
                txtKitPoId.setText("Kit PO ID : " + dto.getId());
                txtKitName.setText("Kit Name : " + dto.getKitName());
                txtKitPo.setText("PO#" + dto.getOrderNo());
                txtKitBatch.setText("Batch : " + dto.getBatchNo());
                txtKitRecDate.setText("Rec. Date : " + dto.getRecDate());
                txtKitManDate.setText("Man. Date : " + dto.getManDate());
                txtKitExpDate.setText("Exp. Date : " + dto.getExpDate());
                txtKitCountry.setText("Country : " + dto.getCountryName());
                txtKitLocation.setText("Location : " + dto.getLocation());
                txtKitBoxes.setText("Boxes Per Pallet : " + dto.getBoxesPerPallet());
                txtKitPallet.setText("Kits Per Pallet : " + dto.getKitsPerPallet());
            }

            @Override
            public void onFailure(Call<KitPoAppDto> call, Throwable t) {
                Toast.makeText(KitPoDetails.this,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}