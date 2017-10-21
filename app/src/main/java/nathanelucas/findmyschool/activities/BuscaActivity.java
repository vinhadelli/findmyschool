package nathanelucas.findmyschool.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;

import nathanelucas.findmyschool.R;
import nathanelucas.findmyschool.RetrofitService;

public class BuscaActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private String estado;
    //private String  nome_escola;
    private EditText nome_escola_field;
    private RetrofitService service;
    CheckBox crecheBox, fundamentalBox, medioBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busca);

        Spinner spinner_estados = (Spinner) findViewById(R.id.spiner_estados);

        iniciarSpinner(spinner_estados, R.array.estados_brasil);

        //nome_escola_field = (EditText) findViewById(R.id.nome_escola_field);
        crecheBox = (CheckBox) findViewById(R.id.crecheBox);
        fundamentalBox = (CheckBox) findViewById(R.id.fundamentalBox);
        medioBox = (CheckBox) findViewById(R.id.medioBox);



    }

    private void iniciarSpinner(Spinner spinner, int arrayid) {
        spinner.setOnItemSelectedListener(this);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, arrayid, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }

    public void buscarLista(View view)
    {
//        Intent i = new Intent(getApplicationContext(), ListaActivity.class);
        Intent i = new Intent(this, ListaActivity.class);
        //this.nome_escola = nome_escola_field.getText().toString();

        if(crecheBox.isChecked())
            i.putExtra("creche","S");
        if(fundamentalBox.isChecked())
            i.putExtra("fundamental","S");
        if(medioBox.isChecked())
            i.putExtra("medio","S");



        //i.putExtra("nome",nome_escola);
        i.putExtra("estado",estado);
        //i.putExtra("retrofit",service);
        startActivity(i);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        String estados[] = getResources().getStringArray(R.array.estados_brasil);
        this.estado = estados[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
