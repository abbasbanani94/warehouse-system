package com.example.warehouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainMenu extends AppCompatActivity {

    TextView txtBarcode;
    String id, type;
    Button btnDetails,btnInventory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        txtBarcode = findViewById(R.id.txtBarcode);
        String code = getIntent().getStringExtra("barcode");
        id = code.substring(1,code.indexOf('.'));
        if(code.startsWith("K")) {
            txtBarcode.setText("Kit PO ID : " + id);
            type = "kit";
        }
        else {
            txtBarcode.setText("Item PO ID : " + id);
            type = "item";
        }

        btnDetails = findViewById(R.id.btnDetails);

        btnDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(type == "item") {
                    Intent intent = new Intent(MainMenu.this,ItemPoDetails.class);
                    Retrofit.Builder builder = new Retrofit.Builder()
                            .baseUrl("http://192.168.8.105:8080")
                            .addConverterFactory(GsonConverterFactory.create());

                    Retrofit retrofit = builder.build();
                    ItemPoClient client = retrofit.create(ItemPoClient.class);
                    Call<ItemPoAppDto> call = client.findItemPoDetailsForApp(Integer.parseInt(id));

                    call.enqueue(new Callback<ItemPoAppDto>() {
                        @Override
                        public void onResponse(Call<ItemPoAppDto> call, Response<ItemPoAppDto> response) {
                            ItemPoAppDto dto = response.body();
                            intent.putExtra("itemPoId", dto.getId());
                            intent.putExtra("itemName", dto.getItemName());
                            intent.putExtra("po", dto.getPurchaseOrderNo());
                            intent.putExtra("batch", dto.getBatchNo());
                            intent.putExtra("packaging", dto.getPackaging());
                            intent.putExtra("recDate", dto.getRecDate().toString());
                            intent.putExtra("manDate", dto.getManDate().toString());
                            intent.putExtra("expDate", dto.getExpDate().toString());
                            intent.putExtra("country", dto.getCountryName());
                            startActivity(intent);
                        }

                        @Override
                        public void onFailure(Call<ItemPoAppDto> call, Throwable t) {
                            Toast.makeText(MainMenu.this,t.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });

        btnInventory = findViewById(R.id.btnInventory);

        btnInventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(type == "item") {
                    Intent intent = new Intent(MainMenu.this, ItemInventory.class);
                    intent.putExtra("itemPoId", id);
                    startActivity(intent);
                }
            }
        });
    }
}