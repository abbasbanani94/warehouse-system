package com.example.warehouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ConfigActivity extends AppCompatActivity {

    EditText txtUrl;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        txtUrl = findViewById(R.id.txtUrl);
        btnSave = findViewById(R.id.btnSaveConfig);

        SharedPreferences preferences = getSharedPreferences("warehouse",MODE_PRIVATE);
        txtUrl.setText(preferences.getString("url",""));

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = txtUrl.getText().toString();
                if (url.isEmpty())
                    Toast.makeText(ConfigActivity.this,"Enter the url",Toast.LENGTH_SHORT).show();
                else {
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("url",url);
                    editor.apply();
                    Toast.makeText(ConfigActivity.this,"Server URL Saved",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}