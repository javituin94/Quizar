package com.example.juanma.quizar;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.sql.SQLException;
import java.util.ArrayList;


public class ResultadosActivity extends ActionBarActivity {

    private BD bd;
    private ListView ls;
    private Button boton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);

        ls=(ListView) findViewById(R.id.lista);
        boton=(Button) findViewById(R.id.btn_back2);

        ArrayList<Resultados> items = listarResultados();
        ResultadosAdapter adapter = new ResultadosAdapter(this, items);
        ls.setAdapter(adapter);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private ArrayList listarResultados() {
        ArrayList<Resultados> lista;
        bd=new BD(getBaseContext());
        try {
            bd.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        lista = bd.getResultados();

        bd.close();

        return lista;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_resultados, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
