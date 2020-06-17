package com.rodmor.listadecompras;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class AdicionarItem extends AppCompatActivity {

    public static int CONDIMENTO = 1, BOLACHA = 2, FRIO = 3, HIGIENE = 4, OUTROS = 5;
    EditText textNome;
    TextView textQuant;
    EditText textPreco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_item);
    }

    public void addItens(View view) {
        TextView quant = findViewById(R.id.text_quantidade);
        int antigo = Integer.parseInt(quant.getText().toString())+1;
        quant.setText(String.valueOf(antigo));
    }

    public void rmItens(View view) {
        TextView quant = findViewById(R.id.text_quantidade);
        int antigo = Integer.parseInt(quant.getText().toString());
        if (antigo > 1){
            antigo--;
            quant.setText(String.valueOf(antigo));
        } else {
            quant.setText("0");
        }
    }

    public void adicionarItem(View view) {

        // Busca das informações na tela
        textNome = findViewById(R.id.edit_nome);
        String nome = textNome.getText().toString();

        RadioGroup radioCategoria = findViewById(R.id.radio_categoria);
        int categoria;
        switch (radioCategoria.getCheckedRadioButtonId()) {
            case R.id.radio_button_cond:
                categoria = CONDIMENTO;
                break;
            case R.id.radio_button_bol:
                categoria = BOLACHA;
                break;
            case R.id.radio_button_frio:
                categoria = FRIO;
                break;
            case R.id.radio_button_higi:
                categoria = HIGIENE;
                break;
            default:
                categoria = OUTROS;
        }

        textQuant = findViewById(R.id.text_quantidade);
        int quant = Integer.parseInt(textQuant.getText().toString());

        textPreco = findViewById(R.id.edit_preco);
        float preco;
        if (!textPreco.getText().toString().isEmpty()) {
            preco = Float.parseFloat(textPreco.getText().toString());
        } else {
            preco = 0.00f;
        }

        if (nome.equals("")) {
            String titulo = "Dados imcompletos";
            String msg = "Dê um nome para o seu novo item!";
            alertDialog(this, titulo, msg);
        } else {
            Item novoItem = new Item(nome,categoria,quant,preco);

            DBHelper db = new DBHelper(getBaseContext());
            SQLiteDatabase banco = db.getWritableDatabase();

            db.insert(banco, novoItem);
            banco.close();

            String titulo = "Item adicionado";
            String msg = "O Item foi adicionado com sucesso!";
            alertDialog(this, titulo, msg);
            limparCampos(this);
        }
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

    public void limparCampos(Context context) {
        textNome.setText("");
        textPreco.setText("");
        textQuant.setText("0");
    }
}