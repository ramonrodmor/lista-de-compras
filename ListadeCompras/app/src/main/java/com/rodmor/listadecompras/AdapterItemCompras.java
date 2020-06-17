package com.rodmor.listadecompras;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

public class AdapterItemCompras extends BaseAdapter {

    DecimalFormat nf = new DecimalFormat("##0.00");
    private final List<Item> itens;
    private final Activity act;

    public AdapterItemCompras(List<Item> itens, Activity act) {
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
        View view = act.getLayoutInflater().inflate(R.layout.item_da_compra, parent, false);
        Item item = itens.get(position);

        //pegando as referências das Views
        TextView nome = view.findViewById(R.id.compras_nome);
        TextView preco = view.findViewById(R.id.compras_valor);
        TextView quant = view.findViewById(R.id.compras_quant);
        Button botaoUncheck = view.findViewById(R.id.compras_uncheck);
        Button botaoCheck = view.findViewById(R.id.compras_check);
        TextView posicao = view.findViewById(R.id.compras_posicao);
        View divider = view.findViewById(R.id.compras_risco);

        //populando as Views
        nome.setText(item.getNome());
        preco.setText(nf.format(item.getPreco()));
        quant.setText(String.valueOf(item.getQuantidade()));
        posicao.setText(String.valueOf(position));

        if (item.getSelecionado()==1) {
            botaoCheck.setVisibility(View.GONE);
            botaoCheck.setClickable(false);
            preco.setEnabled(false);
        } else {
            botaoUncheck.setVisibility(View.GONE);
            botaoUncheck.setClickable(false);
            preco.setEnabled(true);
            divider.setVisibility(View.GONE);
        }

        return view;
    }
}