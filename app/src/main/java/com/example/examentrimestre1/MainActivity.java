package com.example.examentrimestre1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    private static final String PREFERENCIA_BARRA = "Mis Preferencias";
    private ArrayList<Item> items = new ArrayList<>();
    private ArrayList<Item> items_comprats = new ArrayList<>();
    Gson gson = new Gson();
    private Intent i;

    private ArrayAdapter<Item> ai,aic;

    private int CODI_PETICIO = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences pref = getSharedPreferences(PREFERENCIA_BARRA, MODE_PRIVATE);

        String json = pref.getString("items", "paco");
        Type type = new TypeToken<ArrayList<Item>>(){}.getType();
        ArrayList<Item> obj = gson.fromJson(json, type);
        items = obj;





        ListView llista_items = (ListView) findViewById(R.id.llista);
        ai = new ArrayItem(this,R.layout.item_llista,items);
        llista_items.setAdapter(ai);

        ListView llista_items_comprats = (ListView) findViewById(R.id.llistaComprats);
        aic = new ArrayItem(this,R.layout.item_llista,items_comprats);
        llista_items_comprats.setAdapter(aic);

        llista_items.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("ssnjss");
                Toast toast1 = Toast.makeText(getApplicationContext(), "Item nom!", Toast.LENGTH_SHORT);
                toast1.show();            }
        });

        Button btnEnviar = (Button) findViewById(R.id.afegeix);
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // código a ejecutar cuando sea pulsado
                i = new Intent(v.getContext(), NouItem.class);
                startActivityForResult(i,CODI_PETICIO);

            }
        });


    }
    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences pref = getSharedPreferences(PREFERENCIA_BARRA, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        ArrayList<Item> copiaItems = items;
        editor.putString("items", gson.toJson(copiaItems) );
        editor.commit();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == CODI_PETICIO) {
            if (data.hasExtra("Nom") && data.hasExtra("Quantitat")) {
                String nom = data.getStringExtra("Nom");
                String quantitat = data.getStringExtra("Quantitat");
                Item nou_item = new Item(1,nom,quantitat,false);
                items.add(nou_item);

                Toast toast1 = Toast.makeText(getApplicationContext(), "Item afegit!", Toast.LENGTH_SHORT);
                toast1.show();
                ai.notifyDataSetChanged();
            }
        }
    }

    public void actualitza(Item item){
        if(item.isComprat()){
            items.add(item);
            items_comprats.remove(item);
            item.setComprat(false);
        }else{
            items.remove(item);
            items_comprats.add(item);
            item.setComprat(true);
        }


        ai.notifyDataSetChanged();
        aic.notifyDataSetChanged();
    }
}
