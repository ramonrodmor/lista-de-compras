package com.rodmor.listadecompras;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class FragmentoItem extends Fragment {

    private View view;
    private TextView textNome;
    private Button botaoMenos;
    private TextView quant;
    private Button botaoMais;

    public FragmentoItem (Item item) {
        atualizaTextos(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_item_da_lista, container, false);

        textNome = (TextView) view.findViewById(R.id.frag_nome);
        botaoMenos = (Button) view.findViewById(R.id.frag_botao_menos);
        quant = (TextView) view.findViewById(R.id.frag_quant);
        botaoMais = (Button) view.findViewById(R.id.frag_botao_mais);

        botaoMenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragRmItem();
            }
        });
        botaoMais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragAddItem();
            }
        });

        Item item = new Item("Arroz", 1, 7, 3.45f);
        atualizaTextos(item);

        return view;
    }

    public void fragAddItem() {
        int antigo = Integer.parseInt(quant.getText().toString())+1;
        quant.setText(String.valueOf(antigo));
    }

    public void fragRmItem() {
        int antigo = Integer.parseInt(quant.getText().toString());
        if (antigo > 1){
            antigo--;
            quant.setText(String.valueOf(antigo));
        } else {
            quant.setText("0");
        }
    }

    public void atualizaTextos(Item item) {
        setNome(item.getNome());
        setQuant(item.getQuantidade());
        setPreco(item.getPreco());
    }

    public void setNome (String nome) {
        textNome.setText(nome);
    }

    public void setPreco (float preco) {
        TextView textPreco = (TextView) view.findViewById(R.id.frag_preco);
        textPreco.setText(String.valueOf(preco));
    }

    // TODO: revisar uso da função em additem e rmitem
    public void setQuant (int quantidade) {
        TextView textQuant = (TextView) view.findViewById(R.id.frag_quant);
        textQuant.setText(String.valueOf(quantidade));
    }
}