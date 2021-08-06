package com.example.warehouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
                if(type.equals("item")) {
                    Intent intent = new Intent(MainMenu.this, ItemPoDetails.class);
                    intent.putExtra("itemPoId", id);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(MainMenu.this, KitPoDetails.class);
                    intent.putExtra("kitPoId", id);
                    startActivity(intent);
                }
            }
        });

        btnInventory = findViewById(R.id.btnInventory);

        btnInventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenu.this, InventoryActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("type", type);
                startActivity(intent);
            }
        });
    }
}