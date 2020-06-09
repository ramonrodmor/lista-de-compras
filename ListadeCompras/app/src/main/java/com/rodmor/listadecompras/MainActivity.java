package com.rodmor.listadecompras;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Chamada da tela de gerenciamento de listas */
    public void iniciarListas(View view) {
        Intent intent = new Intent(getApplicationContext(), Lista.class);
        startActivity(intent);
    }

    /** Chamada da tela de compras */
    public void iniciarCompras(View view) {
        Intent intent = new Intent(getApplicationContext(), Compras.class);
        startActivity(intent);
    }
}