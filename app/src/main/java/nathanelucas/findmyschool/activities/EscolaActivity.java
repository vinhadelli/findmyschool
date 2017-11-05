package nathanelucas.findmyschool.activities;

import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import nathanelucas.findmyschool.R;

public class EscolaActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private double lati,longi;
    private MapFragment mMapFragment;
    private String nome;

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
        String bairro = null, municipio = null, cep = null, telefone = null, categoria = null;
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
            lati = (double) extras.getFloat("lati");
            longi = (double) extras.getFloat("longi");

        }
        nome_escola.setText(nome);
        bairro_escola.setText(bairro);
        cidade_escola.setText(municipio);
        cep_escola.setText("CEP: " + cep);
        telefone_escola.setText("Telefone: " + telefone);
        categoria_escola.setText("Escola: " + categoria);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng escola = new LatLng(lati,longi);
        Log.e("LOCALIZAÇÃO","Lat: "+lati+" Longi: "+longi);

        mMap.addMarker(new MarkerOptions().position(escola).title(nome));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(escola,18));
    }

    @Override
    protected void onResume() {
        super.onResume();

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.mapview);
        mapFragment.getMapAsync(this);
    }
}
