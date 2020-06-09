package com.rodmor.listadecompras;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Lista extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
    }

    /** Chamada da tela de adição de itens */
    public void addItens(View view) {
        Intent intent = new Intent(getApplicationContext(), AdicionarItem.class);
        startActivity(intent);
    }
}