package com.rodmor.listadecompras;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Lista extends AppCompatActivity {

    FragmentTransaction transaction;
    FragmentManager manager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
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

        List<Item> listaItens = db.select(banco, "nome");
        List<FragmentoItem> listaFragmentos = new ArrayList<FragmentoItem>();

        for (int i = 0; i < listaItens.size(); i++) {

            // TODO: testar listviews em vez de fragments
            FragmentoItem fragmento = new FragmentoItem(listaItens.get(i));
            transaction = manager.beginTransaction();
            transaction.add(R.id.fragment_container, fragmento, null);
            transaction.commit();

            //TextView fragnome = frag.findViewById(R.id.frag_nome);
            //fragnome.setText(listaItens.get(i).getNome());

//            fragmento.setNome(listaItens.get(i).getNome());
//            fragmento.setQuant(listaItens.get(i).getQuantidade());
//            fragmento.setPreco(listaItens.get(i).getPreco());

            listaFragmentos.add(fragmento);
        }
    }
}