package com.rodmor.listadecompras;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
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
                "preco FLOAT," +
                "selecionado INTEGER" +
                ");";
        db.execSQL(sqlLista);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("DBHelper", "onUpgrade");
    }

    public List<Item> select(SQLiteDatabase banco, String ordem1, String ordem2) {
        Cursor cursor = banco.rawQuery(("SELECT id, nome, categoria, quantidade, preco, selecionado FROM lista ORDER BY " + ordem1 + ", " + ordem2), null);

        List<Item> listaItens = new ArrayList<Item>();

        if (cursor.moveToFirst()) {
            do {
                int valorId = (cursor.getInt(cursor.getColumnIndex("id")));
                String valorNome = (cursor.getString(cursor.getColumnIndex("nome")));
                int valorCategoria = (cursor.getInt(cursor.getColumnIndex("categoria")));
                int valorQuantidade = (cursor.getInt(cursor.getColumnIndex("quantidade")));
                float valorValor = (cursor.getFloat(cursor.getColumnIndex("preco")));
                int valorSelecionado = (cursor.getInt(cursor.getColumnIndex("selecionado")));

                Item item = new Item(valorId, valorNome, valorCategoria, valorQuantidade, valorValor, valorSelecionado);
                listaItens.add(item);

            } while (cursor.moveToNext());
        }
        cursor.close();
        return listaItens;
    }

    public List<Item> selectCompras(SQLiteDatabase banco, String ordem1, String ordem2) {
        Cursor cursor = banco.rawQuery(("SELECT id, nome, categoria, quantidade, preco, selecionado FROM lista ORDER BY " + ordem1 + ", " + ordem2), null);

        List<Item> listaItens = new ArrayList<Item>();

        if (cursor.moveToFirst()) {
            do {
                int valorId = (cursor.getInt(cursor.getColumnIndex("id")));
                String valorNome = (cursor.getString(cursor.getColumnIndex("nome")));
                int valorCategoria = (cursor.getInt(cursor.getColumnIndex("categoria")));
                int valorQuantidade = (cursor.getInt(cursor.getColumnIndex("quantidade")));
                float valorValor = (cursor.getFloat(cursor.getColumnIndex("preco")));
                int valorSelecionado = (cursor.getInt(cursor.getColumnIndex("selecionado")));

                Item item = new Item(valorId, valorNome, valorCategoria, valorQuantidade, valorValor, valorSelecionado);
                if (item.getQuantidade() > 0){
                    listaItens.add(item);
                }

            } while (cursor.moveToNext());
        }
        cursor.close();
        return listaItens;
    }

    public void insert(SQLiteDatabase banco, Item item) {
        ContentValues ctv = new ContentValues();
        ctv.put("nome", item.getNome());
        ctv.put("categoria", item.getCategoria());
        ctv.put("quantidade", item.getQuantidade());
        ctv.put("preco", item.getPreco());
        ctv.put("selecionado", item.getSelecionado());

        banco.insert("lista", null, ctv);
    }

    public void updateQtd(SQLiteDatabase banco, int id, int quant) {
        ContentValues ctv = new ContentValues();
        ctv.put("quantidade", quant);

        banco.update("lista", ctv, "id="+id, null);
    }

    public void updatePreco(SQLiteDatabase banco, int id, float preco) {
        ContentValues ctv = new ContentValues();
        ctv.put("preco", preco);

        banco.update("lista", ctv, "id="+id, null);
    }

    public void updateSelecao(SQLiteDatabase banco, int id, int selecionado) {
        ContentValues ctv = new ContentValues();
        ctv.put("selecionado", selecionado);

        banco.update("lista", ctv, "id="+id, null);
    }

    public void updateSelecaoTotal(SQLiteDatabase banco) {
        ContentValues ctv = new ContentValues();
        ctv.put("selecionado", 0);

        banco.update("lista", ctv, null, null);
    }

    public void deleteVazios(SQLiteDatabase banco) {
        String avulso = "avulso";
        banco.delete("lista", "nome LIKE '"+avulso+"'", null);
    }
}
