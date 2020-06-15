package com.rodmor.listadecompras;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lista extends AppCompatActivity {

    ListView listaDeItens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        listaDeItens = (ListView) findViewById(R.id.list_view);
    }

    /** Chamada da tela de adição de itens */
    public void addItens(View view) {
        Intent intent = new Intent(getApplicationContext(), AdicionarItem.class);
        startActivity(intent);
    }

    public void onResume () {

        super.onResume();

        DBHelper db = new DBHelper(getBaseContext());
        SQLiteDatabase banco = db.getReadableDatabase();

        List<Item> itens = db.select(banco, "categoria", "nome");
        Log.d("ListItens", "Qtd: " + String.valueOf(itens.size()));
        List<MeuAdapter> listAdapter = new ArrayList<MeuAdapter>();
        MeuAdapter adapter = new MeuAdapter(itens, this);
        listaDeItens.setAdapter(adapter);

    }
}