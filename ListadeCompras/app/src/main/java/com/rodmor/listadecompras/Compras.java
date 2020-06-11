package com.rodmor.listadecompras;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

public class Compras extends AppCompatActivity {

    float valorLimite = 200.0f;
    TextView textValorTotal;
    TextView textRs;

    // TODO: trabalhar scrols views

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compras);

        textValorTotal = findViewById(R.id.text_valor_total);
        textValorTotal.setText(String.valueOf(0.0f));
        textRs = findViewById(R.id.text_rs);

        atualizaCor();
    }

    public void somaTotal (float valor) {
        float novoTotal = Float.parseFloat(textValorTotal.getText().toString()) + valor;
        textValorTotal.setText(String.valueOf(novoTotal));
        atualizaCor();
    }

    public void diminuiTotal (float valor) {
        float novoTotal = Float.parseFloat(textValorTotal.getText().toString()) + valor;
        textValorTotal.setText(String.valueOf(novoTotal));
        atualizaCor();
    }

    public void atualizaCor () {
        if (Float.parseFloat(textValorTotal.getText().toString()) > valorLimite) {
            textValorTotal.setTextColor(Color.RED);
            textRs.setTextColor(Color.RED);
        } else {
            textValorTotal.setTextColor(Color.GREEN);
            textRs.setTextColor(Color.GREEN);
        }
    }
}