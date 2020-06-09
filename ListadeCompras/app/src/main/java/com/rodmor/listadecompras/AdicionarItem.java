package com.rodmor.listadecompras;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import static com.rodmor.listadecompras.Categoria.BOLACHA;
import static com.rodmor.listadecompras.Categoria.CONDIMENTO;
import static com.rodmor.listadecompras.Categoria.FRIO;
import static com.rodmor.listadecompras.Categoria.HIGIENE;
import static com.rodmor.listadecompras.Categoria.OUTROS;

public class AdicionarItem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_item);
    }

    public void addItens(View view) {
        TextView quant = findViewById(R.id.quantidade);
        int antigo = Integer.parseInt(quant.getText().toString())+1;
        quant.setText(""+antigo);
    }

    public void rmItens(View view) {
        TextView quant = findViewById(R.id.quantidade);
        int antigo = Integer.parseInt(quant.getText().toString());
        if (antigo > 1){
            antigo--;
            quant.setText(""+antigo);
        } else {
            quant.setText("0");
        }
    }

    public void adicionarItem(View view) {
        EditText textNome = findViewById(R.id.editNome);
        String nome = textNome.getText().toString();

        // categoria
        RadioGroup radioCategoria = findViewById(R.id.radioCategoria);
        Categoria categoria;
        switch (radioCategoria.getCheckedRadioButtonId()) {
            case R.id.radioButtonCond:
                categoria = CONDIMENTO;
                break;
            case R.id.radioButtonBol:
                categoria = BOLACHA;
                break;
            case R.id.radioButtonFrio:
                categoria = FRIO;
                break;
            case R.id.radioButtonHigi:
                categoria = HIGIENE;
                break;
            default:
                categoria = OUTROS;
        }

        TextView textQuant = findViewById(R.id.quantidade);
        int quant = Integer.parseInt(textQuant.getText().toString());

        EditText textPreco = findViewById(R.id.editPreco);
        float preco = Float.parseFloat(textPreco.getText().toString());

        Item novo = new Item(nome, categoria, quant, preco);
    }
}