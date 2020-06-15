package com.rodmor.listadecompras;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

class MinhaViewHolder{

    private TextView textNome;
    private Button botaoMenos;
    private TextView quant;
    private Button botaoMais;

    MinhaViewHolder(View v){
        textNome = (TextView) v.findViewById(R.id.frag_nome);
        botaoMenos = (Button) v.findViewById(R.id.frag_botao_menos);
        quant = (TextView) v.findViewById(R.id.frag_quant);
        botaoMais = (Button) v.findViewById(R.id.frag_botao_mais);
    }
}