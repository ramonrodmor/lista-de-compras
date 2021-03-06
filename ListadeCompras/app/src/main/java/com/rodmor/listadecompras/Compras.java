package com.rodmor.listadecompras;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import java.text.DecimalFormat;
import java.util.List;

public class Compras extends AppCompatActivity {

    DecimalFormat nf = new DecimalFormat("##0.00");
    float valorLimite = 200.0f;
    TextView textValorTotal;
    TextView textRs;
    ListView listaDeCompras;
    List<Item> itens = null;

    // TODO: trabalhar scrols views

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compras);

        textValorTotal = findViewById(R.id.text_valor_total);
        textValorTotal.setText(nf.format(0f));
        textRs = findViewById(R.id.text_rs);
        atualizaCorSaldo();

        listaDeCompras = findViewById(R.id.compras_view);
    }

    public void onResume() {
        super.onResume();

        DBHelper db = new DBHelper(getBaseContext());
        SQLiteDatabase banco = db.getReadableDatabase();

        itens = db.selectCompras(banco, "categoria", "nome");
        for (int i=0; i<itens.size(); i++) {
            if (itens.get(i).getSelecionado() == 1) {
                somaTotal(itens.get(i).getPreco() * (float) itens.get(i).getQuantidade());
            }
        }
        AdapterItemCompras adapter = new AdapterItemCompras(itens, this);
        listaDeCompras.setAdapter(adapter);

        banco.close();
    }

    public void clickCheck(View v) {
        // pega linha em que o click foi dado
        ConstraintLayout vwParentRow = (ConstraintLayout) v.getParent();

        TextView quant = (TextView) vwParentRow.getViewById(R.id.compras_quant);
        EditText preco = (EditText) vwParentRow.getViewById(R.id.compras_valor);
        Button check = (Button) vwParentRow.getViewById(R.id.compras_check);
        Button uncheck = (Button) vwParentRow.getViewById(R.id.compras_uncheck);
        TextView posicao = (TextView) vwParentRow.getViewById(R.id.compras_posicao);
        View divider = vwParentRow.getViewById(R.id.compras_risco);

        int indice = Integer.parseInt(posicao.getText().toString());
        float novoPreco = Float.parseFloat(preco.getText().toString().replace(",","."));
        float valorTotal = Float.parseFloat(quant.getText().toString())*novoPreco;
        somaTotal(valorTotal);

        itens.get(indice).setSelecionado(1);
        check.setVisibility(View.GONE);
        check.setClickable(false);
        uncheck.setVisibility(View.VISIBLE);
        uncheck.setClickable(true);
        preco.setEnabled(false);
        divider.setVisibility(View.VISIBLE);

        itens.get(indice).setPreco(novoPreco);
        atualizaPrecoBanco(itens.get(indice).getId(), novoPreco);
        atualizaSelecaoBanco(itens.get(indice).getId(), itens.get(indice).getSelecionado());
    }

    public void clickUncheck(View v) {
        // pega linha em que o click foi dado
        ConstraintLayout vwParentRow = (ConstraintLayout) v.getParent();

        TextView quant = (TextView) vwParentRow.getViewById(R.id.compras_quant);
        EditText preco = (EditText) vwParentRow.getViewById(R.id.compras_valor);
        Button check = (Button) vwParentRow.getViewById(R.id.compras_check);
        Button uncheck = (Button) vwParentRow.getViewById(R.id.compras_uncheck);
        TextView posicao = (TextView) vwParentRow.getViewById(R.id.compras_posicao);
        View divider = vwParentRow.getViewById(R.id.compras_risco);

        int indice = Integer.parseInt(posicao.getText().toString());
        float novoPreco = Float.parseFloat(preco.getText().toString().replace(",","."));
        float valorTotal = Float.parseFloat(quant.getText().toString())*novoPreco;
        diminuiTotal(valorTotal);

        itens.get(indice).setSelecionado(0);
        uncheck.setVisibility(View.GONE);
        uncheck.setClickable(false);
        check.setVisibility(View.VISIBLE);
        check.setClickable(true);
        preco.setEnabled(true);
        divider.setVisibility(View.GONE);
        atualizaSelecaoBanco(itens.get(indice).getId(), itens.get(indice).getSelecionado());
    }

    public void somaTotal(float preco) {
        float novoTotal = Float.parseFloat(textValorTotal.getText().toString().replace(",",".")) + preco;
        textValorTotal.setText(nf.format(novoTotal));
        atualizaCorSaldo();
    }

    public void diminuiTotal(float preco) {
        float novoTotal = Float.parseFloat(textValorTotal.getText().toString().replace(",",".")) - preco;
        textValorTotal.setText(nf.format(novoTotal));
        atualizaCorSaldo();
    }

    public void somaAvulso(View v){
        // pega linha em que o click foi dado
        ConstraintLayout vwParentRow = (ConstraintLayout) v.getParent();

        EditText editValor = (EditText) vwParentRow.getViewById(R.id.compras_valor);
        float valor;
        if (!editValor.getText().toString().isEmpty()) {
            valor = Float.parseFloat(editValor.getText().toString().replace(",","."));
        } else {
            valor = 0.00f;
        }
        editValor.setText("");
        Item itemNovo = new Item(valor);
        adicionaNoBanco(itemNovo);
        onResume();
    }

    public void atualizaCorSaldo() {
        if (Float.parseFloat(textValorTotal.getText().toString().replace(",",".")) > valorLimite) {
            textValorTotal.setTextColor(Color.RED);
            textRs.setTextColor(Color.RED);
        } else {
            textValorTotal.setTextColor(Color.GREEN);
            textRs.setTextColor(Color.GREEN);
        }
    }

    public void atualizaPrecoBanco(int id, float preco) {
        DBHelper db = new DBHelper(getBaseContext());
        SQLiteDatabase banco = db.getReadableDatabase();
        db.updatePreco(banco, id, preco);
        banco.close();
    }

    public void atualizaSelecaoBanco(int id, int selecionado) {
        DBHelper db = new DBHelper(getBaseContext());
        SQLiteDatabase banco = db.getReadableDatabase();
        db.updateSelecao(banco, id, selecionado);
        banco.close();
    }

    public void finalizarCompras(View v){
        DBHelper db = new DBHelper(getBaseContext());
        SQLiteDatabase banco = db.getReadableDatabase();
        db.updateSelecaoTotal(banco);
        db.deleteVazios(banco);
        banco.close();
        String titulo = "Compra finalizada";
        String msg = "Compra finalizada com sucesso! Sua lista de compras foi redefinida.";
        alertDialog(this, titulo, msg);
    }

    public void adicionaNoBanco(Item itemNovo){
        DBHelper db = new DBHelper(getBaseContext());
        SQLiteDatabase banco = db.getReadableDatabase();
        db.insert(banco, itemNovo);
        banco.close();
    }

    public void alertDialog(Context contexto, String titulo, String msg) {

        AlertDialog.Builder builder = new AlertDialog.Builder(contexto);
        builder.setMessage(msg);
        builder.setTitle(titulo);

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
                finish();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}