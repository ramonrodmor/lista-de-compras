package com.rodmor.listadecompras;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import java.util.List;
//TODO: não permitir alteração de itens selecionados
public class Lista extends AppCompatActivity {

    ListView listaDeItens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        listaDeItens = findViewById(R.id.list_view);
    }

    /** Chamada da tela de adição de itens */
    public void addItens(View view) {
        Intent intent = new Intent(getApplicationContext(), AdicionarItem.class);
        startActivity(intent);
    }

    public void onResume() {

        super.onResume();

        DBHelper db = new DBHelper(getBaseContext());
        SQLiteDatabase banco = db.getReadableDatabase();

        List<Item> itens = db.select(banco, "categoria", "nome");
        db.close();

        AdapterItemLista adapter = new AdapterItemLista(itens, this);
        listaDeItens.setAdapter(adapter);
    }

    public void clickMais(View v) {
        // pega linha em que o click foi dado
        ConstraintLayout vwParentRow = (ConstraintLayout) v.getParent();

        TextView id = (TextView) vwParentRow.getViewById(R.id.frag_id);
        TextView quant = (TextView) vwParentRow.getViewById(R.id.frag_quant);
        int novaQtd = Integer.parseInt(quant.getText().toString())+1;
        quant.setText(String.valueOf(novaQtd));
        atualizaItemBanco(Integer.parseInt(id.getText().toString()), novaQtd);
    }

    public void clickMenos(View v) {
        // pega linha em que o click foi dado
        ConstraintLayout vwParentRow = (ConstraintLayout) v.getParent();

        TextView id = (TextView) vwParentRow.getViewById(R.id.frag_id);
        TextView quant = (TextView) vwParentRow.getViewById(R.id.frag_quant);
        int antigo = Integer.parseInt(quant.getText().toString());
        if (antigo > 0){
            int novaQtd=antigo-1;
            quant.setText(String.valueOf(novaQtd));
            atualizaItemBanco(Integer.parseInt(id.getText().toString()), novaQtd);
        }
    }

    public void atualizaItemBanco(int id, int qtd) {
        DBHelper db = new DBHelper(getBaseContext());
        SQLiteDatabase banco = db.getReadableDatabase();
        db.updateQtd(banco, id, qtd);
        banco.close();
    }
}