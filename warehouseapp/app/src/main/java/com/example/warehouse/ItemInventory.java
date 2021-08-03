package com.example.warehouse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ItemInventory extends AppCompatActivity {

    RecyclerView recyclerView;
    ItemInventoryAdapter adapter;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_inventory);

        id = getIntent().getStringExtra("itemPoId");

        recyclerView = findViewById(R.id.itemRecyclerView);
        getList();
    }

    private void getList() {
        try {
            if(id != null && !id.isEmpty()) {
                Retrofit.Builder builder = new Retrofit.Builder()
                        .baseUrl("http://192.168.8.105:8080")
                        .addConverterFactory(GsonConverterFactory.create());

                Retrofit retrofit = builder.build();
                ItemPoClient client = retrofit.create(ItemPoClient.class);
                Call<List<ItemInventoryAppDto>> call = client.findItemPoInventoryForApp(Integer.parseInt(id));

                call.enqueue(new Callback<List<ItemInventoryAppDto>>() {
                    @Override
                    public void onResponse(Call<List<ItemInventoryAppDto>> call, Response<List<ItemInventoryAppDto>> response) {
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(new LinearLayoutManager(ItemInventory.this));
                        adapter = new ItemInventoryAdapter(ItemInventory.this, response.body());
                        recyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(Call<List<ItemInventoryAppDto>> call, Throwable t) {
                        Toast.makeText(ItemInventory.this,t.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            Toast.makeText(ItemInventory.this,ex.getMessage(),Toast.LENGTH_LONG).show();
        }
    }
}