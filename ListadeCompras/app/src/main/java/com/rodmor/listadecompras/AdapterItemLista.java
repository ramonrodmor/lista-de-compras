package com.rodmor.listadecompras;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

public class AdapterItemLista extends BaseAdapter {

    DecimalFormat nf = new DecimalFormat("##0.00");
    private final List<Item> itens;
    private final Activity act;
    private TextView quant;

    public AdapterItemLista(List<Item> itens, Activity act) {
        this.itens = itens;
        this.act = act;
    }

    @Override public int getCount() {
        return itens.size();
    }

    @Override public Object getItem(int position) {
        return itens.get(position);
    }

    @Override public long getItemId(int position) {
        return 0;
    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {
        View view = act.getLayoutInflater().inflate(R.layout.item_da_lista, parent, false);
        Item item = itens.get(position);

        //pegando as referências das Views
        TextView id = view.findViewById(R.id.frag_id);
        TextView nome = view.findViewById(R.id.frag_nome);
        TextView preco = view.findViewById(R.id.frag_preco);
        quant = view.findViewById(R.id.frag_quant);

        //populando as Views
        id.setText(String.valueOf(item.getId()));
        nome.setText(item.getNome());
        preco.setText(nf.format(item.getPreco()));
        quant.setText(String.valueOf(item.getQuantidade()));

        return view;
    }
}