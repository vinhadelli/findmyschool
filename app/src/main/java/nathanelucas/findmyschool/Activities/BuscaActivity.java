package nathanelucas.findmyschool.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;

import nathanelucas.findmyschool.R;
import nathanelucas.findmyschool.RetrofitService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BuscaActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private String estado;
    private String  nome_escola;
    private EditText nome_escola_field;
    private RetrofitService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busca);

        Spinner spinner = (Spinner) findViewById(R.id.spiner_estados);
        spinner.setOnItemSelectedListener(this);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.estados_brasil, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        nome_escola_field = (EditText) findViewById(R.id.nome_escola_field);



    }

    public void buscarLista(View view)
    {

        this.nome_escola = nome_escola_field.getText().toString();
        Intent i = new Intent(getApplicationContext(), ListaActivity.class);

        i.putExtra("nome",nome_escola);
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
