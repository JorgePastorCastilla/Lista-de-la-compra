package com.example.examentrimestre1;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class ArrayItem extends ArrayAdapter<Item> {

    //Variables Globals
    private Context context;
    private ArrayList<Item> items;
    public static ArrayList<Boolean> estat = null;

    //Constructor
    public ArrayItem(@NonNull Context context, int resource, @NonNull ArrayList<Item> objects) {
        super(context, resource, objects);
        this.context = context;
        this.items = objects;

    }

    //es crida a l'hora de representar la llista
    public View getView(final int position, final View convertView, ViewGroup parent) {
        //agafam el Item per posició a l'Array
        final Item item = items.get(position);
        //agafam "l'inflater" per "inflar" el layout per a cada item
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_llista, null);

        //instanciam cada element del layout a utilitzar

        TextView nom = (TextView) view.findViewById(R.id.nom);
        TextView quantitat = (TextView) view.findViewById(R.id.quantitat);
        final CheckBox comprat = (CheckBox) view.findViewById(R.id.checkbox);
        //omplim les dades
        nom.setText(item.getNom());
        quantitat.setText(item.getQuantitat());

        comprat.setChecked(item.isComprat());

        comprat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (context instanceof MainActivity) {
                    ((MainActivity)context).actualitza(item);
                }
            }
        });

        return view;
    }
}
