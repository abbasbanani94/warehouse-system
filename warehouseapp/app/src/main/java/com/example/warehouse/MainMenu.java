package com.example.warehouse;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainMenu extends AppCompatActivity {

    TextView txtBarcode;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        txtBarcode = findViewById(R.id.txtBarcode);
        String code = getIntent().getStringExtra("barcode");
        id = code.substring(1,code.indexOf('.'));
        if(code.startsWith("K"))
            txtBarcode.setText("Kit PO ID : " + id);
        else
            txtBarcode.setText("Item PO ID : " + id);
    }
}