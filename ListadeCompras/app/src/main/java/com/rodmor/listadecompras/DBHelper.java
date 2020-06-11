package com.rodmor.listadecompras;

import android.animation.StateListAnimator;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "listadecompras.db";
    private static final int VERSAO_BANCO = 1;

    public DBHelper(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sqlLista = "CREATE TABLE IF NOT EXISTS lista (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome VARCHAR (255)," +
                "categoria INTEGER," +
                "quantidade INTEGER," +
                "preco FLOAT" +
                ");";
        db.execSQL(sqlLista);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("DBHelper", "onUpgrade");
    }

    public List<Item> select(SQLiteDatabase banco, String ordem) {
        Cursor cursor = banco.rawQuery(("SELECT nome, categoria, quantidade, preco FROM lista ORDER BY " + ordem), null);

        List<Item> listaItens = new ArrayList<Item>();

        if (cursor.moveToFirst()) {
            do {
                String valorNome = (cursor.getString(cursor.getColumnIndex("nome")));
                int valorCategoria = (cursor.getInt(cursor.getColumnIndex("categoria")));
                int valorQuantidade = (cursor.getInt(cursor.getColumnIndex("quantidade")));
                float valorValor = (cursor.getFloat(cursor.getColumnIndex("preco")));

                Item item = new Item(valorNome, valorCategoria, valorQuantidade, valorValor);
                listaItens.add(item);

            } while (cursor.moveToNext());
        }
        cursor.close();
        return listaItens;
    }

    public List<Item> select(SQLiteDatabase banco, String where, String valor, String ordem) {
        Cursor cursor = banco.rawQuery(("SELECT nome, categoria, quantidade, preco FROM lista WHERE " + where + "= '" + valor + "' ORDER BY " + ordem), null);

        List<Item> listaItens = new ArrayList<Item>();

        if (cursor.moveToFirst()) {
            do {
                String valorNome = (cursor.getString(cursor.getColumnIndex("nome")));
                int valorCategoria = (cursor.getInt(cursor.getColumnIndex("categoria")));
                int valorQuantidade = (cursor.getInt(cursor.getColumnIndex("quantidade")));
                float valorValor = (cursor.getFloat(cursor.getColumnIndex("preco")));

                Item item = new Item(valorNome, valorCategoria, valorQuantidade, valorValor);
                listaItens.add(item);

            } while (cursor.moveToNext());
        }
        cursor.close();
        return listaItens;
    }

    public void insert (SQLiteDatabase banco, Item item) {
        ContentValues ctv = new ContentValues();
        ctv.put("nome", item.getNome());
        ctv.put("categoria", item.getCategoria());
        ctv.put("quantidade", item.getQuantidade());
        ctv.put("preco", item.getPreco());

        banco.insert("lista", null, ctv);
    }
}
