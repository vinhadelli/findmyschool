package nathanelucas.findmyschool.activities;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import java.util.LinkedList;
import java.util.List;

import nathanelucas.findmyschool.R;
import nathanelucas.findmyschool.RetrofitService;
import nathanelucas.findmyschool.resposta_api.Avaliacoes;
import nathanelucas.findmyschool.resposta_api.Escola;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EscolaActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private double lati,longi;
    private MapFragment mMapFragment;
    private String nome;
    private TextView nota_escola;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escola);


        mMapFragment = MapFragment.newInstance();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.mapview, mMapFragment);
        fragmentTransaction.commit();


        TextView nome_escola = (TextView) findViewById(R.id.nome_escola);
        TextView bairro_escola = (TextView) findViewById(R.id.bairro_escola);
        TextView cidade_escola = (TextView) findViewById(R.id.cidade_escola);
        TextView cep_escola = (TextView) findViewById(R.id.cep_escola);
        TextView telefone_escola = (TextView) findViewById(R.id.telefone_escola);
        TextView categoria_escola = (TextView) findViewById(R.id.categoria_escola);
        nota_escola = (TextView) findViewById(R.id.nota_escola);
        String bairro = null, municipio = null, cep = null, telefone = null, categoria = null;
        int codEscola = 0;
        nome = null;

//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.mapview);
//        mapFragment.getMapAsync(this);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            nome = extras.getString("nome");
            bairro = extras.getString("bairro");
            municipio = extras.getString("municipio");
            cep = extras.getString("cep");
            telefone = extras.getString("telefone");
            categoria = extras.getString("categoria");
            lati = extras.getDouble("lati");
            longi = extras.getDouble("longi");
            codEscola = extras.getInt("codEscola");

        }
        nome_escola.setText(nome);
        bairro_escola.setText(bairro);
        cidade_escola.setText(municipio);
        cep_escola.setText("CEP: " + cep);
        telefone_escola.setText("Telefone: " + telefone);
        categoria_escola.setText("Escola: " + categoria);
        getNota(codEscola);


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng escola = new LatLng(lati,longi);
//        Log.e("LOCALIZAÇÃO","Lat: "+lati+" Longi: "+longi);

        mMap.addMarker(new MarkerOptions().position(escola).title(nome));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(escola,12));
    }

    @Override
    protected void onResume() {
        super.onResume();

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.mapview);
        mapFragment.getMapAsync(this);
    }

    public void startComments(View v){
        Intent intent = new Intent(this, EscolaCommentsActivity.class);
        intent.putExtra("nome", nome);
        startActivity(intent);
    }

    private void getNota(int codEscola){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitService.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();

        RetrofitService service = retrofit.create(RetrofitService.class);

        Call<List<Avaliacoes>> getEscolas = service.getAvaliacoes(codEscola);

        getEscolas.enqueue(new retrofit2.Callback<List<Avaliacoes>>() {
            LinkedList<Avaliacoes> notas = new LinkedList();

            @Override
            public void onResponse(Call<List<Avaliacoes>> call, retrofit2.Response<List<Avaliacoes>> response)
            {
                if(response.isSuccessful()){
                    for (Avaliacoes avaliacao : response.body()){
                        notas.add(avaliacao);
                    }

                    if(notas.size() == 0) return;
                    String text = "Nota: " + Float.toString(notas.getLast().getValor());
                    nota_escola.setText(text);

                }
                else {
                    Toast.makeText(EscolaActivity.this, "Não foi possivel resgatar os dados!", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<Avaliacoes>> call, Throwable t){
                Toast.makeText(EscolaActivity.this, "A conexão falhou!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}