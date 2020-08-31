package com.rodmor.listadecompras;

import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class Config extends AppCompatActivity {

    DecimalFormat nf = new DecimalFormat("##0.00");
    TextView editValorMeta;
    String meta = "meta";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        editValorMeta = findViewById(R.id.edit_limite_valor);
    }

    public void onResume() {
        super.onResume();

        DBHelper db = new DBHelper(getBaseContext());
        SQLiteDatabase banco = db.getReadableDatabase();

        float valorMeta = db.selectVariaveis(banco,meta);
        if (valorMeta<0) valorMeta=0;

        banco.close();

        editValorMeta.setText(nf.format(valorMeta));

    }

    public void fazerBackup() {
        //TODO: fazer rotina de backup
    }

    public void restaurarBackup() {
        //TODO: fazer rotina de restauração
    }

    public void atualizaMeta(View v) {
        float novoValor = Float.parseFloat(editValorMeta.getText().toString().replace(",","."));
        DBHelper db = new DBHelper(getBaseContext());
        SQLiteDatabase banco = db.getReadableDatabase();
        db.updateVariaveis(banco, meta, novoValor);
        banco.close();

        String titulo = "Valor atualizado";
        String msg = "O valor da meta foi atualizado com sucesso!";
        alertDialog(this, titulo, msg);
    }

    public void alertDialog(Context contexto, String titulo, String msg) {

        AlertDialog.Builder builder = new AlertDialog.Builder(contexto);
        builder.setMessage(msg);
        builder.setTitle(titulo);

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
