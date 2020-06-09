package com.rodmor.listadecompras;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

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
                "valor FLOAT" +
                ");";
        db.execSQL(sqlLista);
        Log.d("DATABASE", "Banco criado?");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("DBHelper", "onUpgrade");
    }
}
