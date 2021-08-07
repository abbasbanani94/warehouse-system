package com.example.warehouse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InventoryActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    InventoryAdapter adapter;
    String id,type,url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_inventory);

        SharedPreferences preferences = getSharedPreferences("warehouse",MODE_PRIVATE);
        url = preferences.getString("url","");

        id = getIntent().getStringExtra("id");
        type = getIntent().getStringExtra("type");

        recyclerView = findViewById(R.id.itemRecyclerView);
        getList();
    }

    private void getList() {
        try {
            if(id != null && !id.isEmpty()) {
                Retrofit.Builder builder = new Retrofit.Builder()
                        .baseUrl(url)
                        .addConverterFactory(GsonConverterFactory.create());

                Retrofit retrofit = builder.build();
                if(type.equals("item")) {
                    ItemPoClient client = retrofit.create(ItemPoClient.class);
                    Call<List<InventoryAppDto>> call = client.findItemPoInventoryForApp(Integer.parseInt(id));

                    call.enqueue(new Callback<List<InventoryAppDto>>() {
                        @Override
                        public void onResponse(Call<List<InventoryAppDto>> call, Response<List<InventoryAppDto>> response) {
                            recyclerView.setHasFixedSize(true);
                            recyclerView.setLayoutManager(new LinearLayoutManager(InventoryActivity.this));
                            adapter = new InventoryAdapter(InventoryActivity.this, response.body());
                            recyclerView.setAdapter(adapter);
                        }

                        @Override
                        public void onFailure(Call<List<InventoryAppDto>> call, Throwable t) {
                            Toast.makeText(InventoryActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    });
                }
                else {
                    KitPoClient client = retrofit.create(KitPoClient.class);
                    Call<List<InventoryAppDto>> call = client.findKitPoInventoryForApp(Integer.parseInt(id));

                    call.enqueue(new Callback<List<InventoryAppDto>>() {
                        @Override
                        public void onResponse(Call<List<InventoryAppDto>> call, Response<List<InventoryAppDto>> response) {
                            recyclerView.setHasFixedSize(true);
                            recyclerView.setLayoutManager(new LinearLayoutManager(InventoryActivity.this));
                            adapter = new InventoryAdapter(InventoryActivity.this, response.body());
                            recyclerView.setAdapter(adapter);
                        }

                        @Override
                        public void onFailure(Call<List<InventoryAppDto>> call, Throwable t) {
                            Toast.makeText(InventoryActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            Toast.makeText(InventoryActivity.this,ex.getMessage(),Toast.LENGTH_LONG).show();
        }
    }
}