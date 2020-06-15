package com.rodmor.listadecompras;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MeuAdapter extends BaseAdapter {

    private final List<Item> itens;
    private final Activity act;

    public MeuAdapter(List<Item> itens, Activity act) {
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
        TextView nome = (TextView) view.findViewById(R.id.frag_nome);
        TextView preco = (TextView) view.findViewById(R.id.frag_preco);
        TextView quant = (TextView) view.findViewById(R.id.frag_quant);

        //populando as Views
        nome.setText(item.getNome());
        preco.setText(String.valueOf(item.getPreco()));
        quant.setText(String.valueOf(item.getQuantidade()));
        return view;
    }

}
 /*
    Context c;

    MeuAdapter(Context context){
        this.c = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        MinhaViewHolder holder = null;
        if(row == null){
            LayoutInflater inflater = (LayoutInflater) c
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.item_da_lista, parent, false);
            holder = new MinhaViewHolder(row);
            row.setTag(holder);
        }else{
            holder = (MinhaViewHolder) row.getTag();
        }

        holder.tv_texto.setText(lista.get(position).TvTitulo);
        holder.bt_botao.setText(lista.get(position).BtTitulo);

        return row;
    }

    @Override
    public int getCount() {
        return lista.size();//retorna o tamanho da lista
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);//retorna um item da lista
    }

    @Override
    public long getItemId(int position) {
        return position;//retorna a posição de um item
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
*/