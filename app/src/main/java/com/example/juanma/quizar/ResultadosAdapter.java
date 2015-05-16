package com.example.juanma.quizar;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Juanma on 13/05/2015.
 */
public class ResultadosAdapter extends BaseAdapter {
    protected Activity activity;
    protected ArrayList<Resultados> items;

    public ResultadosAdapter(Activity activity, ArrayList<Resultados> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return items.get(position).getId();
    }

    @Override
    public View getView(int position, View contentView, ViewGroup parent) {
        View vi=contentView;

        if(contentView == null) {
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            vi = inflater.inflate(R.layout.lista, null);
        }

        Resultados item = items.get(position);

        TextView nombre = (TextView) vi.findViewById(R.id.puntos);
        nombre.setText(Integer.toString(item.getPuntos()));



        return vi;
    }
}

