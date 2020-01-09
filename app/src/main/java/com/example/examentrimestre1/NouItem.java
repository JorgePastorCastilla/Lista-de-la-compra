package com.example.examentrimestre1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NouItem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nou_item);

        Button btnEnviar= (Button) findViewById(R.id.afegeix);
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                EditText nom = findViewById(R.id.nom);
                EditText quantitat = findViewById(R.id.quantitat);

                String nom_string = nom.getText().toString();
                String quantitat_string = quantitat.getText().toString();

                Intent intent = new Intent();
                intent.putExtra("Nom", nom_string);
                intent.putExtra("Quantitat", quantitat_string);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }



}
