package com.rodmor.listadecompras;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class AdicionarItem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_item);

        DBHelper db = new DBHelper(getBaseContext());
        db.getWritableDatabase();
    }

    public void addItens(View view) {
        TextView quant = findViewById(R.id.text_quantidade);
        int antigo = Integer.parseInt(quant.getText().toString())+1;
        quant.setText(""+antigo);
    }

    public void rmItens(View view) {
        TextView quant = findViewById(R.id.text_quantidade);
        int antigo = Integer.parseInt(quant.getText().toString());
        if (antigo > 1){
            antigo--;
            quant.setText(""+antigo);
        } else {
            quant.setText("0");
        }
    }

    public void adicionarItem(View view) {

        // Busca das informações na tela
        EditText textNome = findViewById(R.id.edit_nome);
        String nome = textNome.getText().toString();

        RadioGroup radioCategoria = findViewById(R.id.radio_categoria);
        int categoria;
        switch (radioCategoria.getCheckedRadioButtonId()) {
            case R.id.radio_button_cond:
                categoria = 1;
                break;
            case R.id.radio_button_bol:
                categoria = 2;
                break;
            case R.id.radio_button_frio:
                categoria = 3;
                break;
            case R.id.radio_button_higi:
                categoria = 4;
                break;
            default:
                categoria = 0;
        }

        TextView textQuant = findViewById(R.id.text_quantidade);
        int quant = Integer.parseInt(textQuant.getText().toString());

        EditText textPreco = findViewById(R.id.edit_preco);
        float preco;
        if (!textPreco.getText().toString().isEmpty()) {
            preco = Float.parseFloat(textPreco.getText().toString());
        } else {
            preco = Float.parseFloat(textPreco.getHint().toString());
        }

        Item novoItem = new Item(nome,categoria,quant,preco);

        DBHelper db = new DBHelper(getBaseContext());
        SQLiteDatabase banco = db.getWritableDatabase();

        db.insert(banco, novoItem);

        // TODO: adicionar dialog com "ok"

        finish();
    }
}