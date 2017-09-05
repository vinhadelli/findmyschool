package nathanelucas.findmyschool.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import nathanelucas.findmyschool.R;
import nathanelucas.findmyschool.Resposta_API.Escola;

public class EscolaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escola);

        TextView nome_escola = (TextView) findViewById(R.id.nome_escola);
        TextView bairro_escola = (TextView) findViewById(R.id.bairro_escola);
        TextView cidade_escola = (TextView) findViewById(R.id.cidade_escola);
        TextView cep_escola = (TextView) findViewById(R.id.cep_escola);
        TextView telefone_escola = (TextView) findViewById(R.id.telefone_escola);
        TextView categoria_escola = (TextView) findViewById(R.id.categoria_escola);
        String nome = null,bairro = null,municipio = null,cep = null,telefone = null,categoria = null;

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            nome = extras.getString("nome");
            bairro = extras.getString("bairro");
            municipio = extras.getString("municipio");
            cep = extras.getString("cep");
            telefone = extras.getString("telefone");
            categoria = extras.getString("categoria");

        }
        nome_escola.setText(nome);
        bairro_escola.setText(bairro);
        cidade_escola.setText(municipio);
        cep_escola.setText("CEP: "+cep);
        telefone_escola.setText("Telefone: "+telefone);
        categoria_escola.setText("Escola: "+categoria);

    }
}
