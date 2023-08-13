package com.example.imagecapture;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity
{
    Button btndatos, btnlistas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btndatos = (Button) findViewById(R.id.btndatos);
        btnlistas = (Button) findViewById(R.id.btnlistas);
        btndatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { ingresar(); }
        });
        btnlistas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { listas(); }
        });
    }

    private void listas() {
        setContentView(R.layout.activity_list);
    }

    private void ingresar()
    {
        setContentView(R.layout.activity_ingresar);
    }
}