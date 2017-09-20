package nathanelucas.findmyschool.Activities;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import nathanelucas.findmyschool.R;
import nathanelucas.findmyschool.Resposta_API.Escola;

public class EscolaActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener{

    private GoogleMap mMap;
    private double lati,longi;
    private SupportMapFragment mMapFragment;
    private String nome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escola);

//
//        mMapFragment = MapFragment.newInstance();
//        FragmentTransaction fragmentTransaction =
//                getFragmentManager().beginTransaction();
//        fragmentTransaction.add(R.id.mapview, mMapFragment);
//        fragmentTransaction.commit();


        TextView nome_escola = (TextView) findViewById(R.id.nome_escola);
        TextView bairro_escola = (TextView) findViewById(R.id.bairro_escola);
        TextView cidade_escola = (TextView) findViewById(R.id.cidade_escola);
        TextView cep_escola = (TextView) findViewById(R.id.cep_escola);
        TextView telefone_escola = (TextView) findViewById(R.id.telefone_escola);
        TextView categoria_escola = (TextView) findViewById(R.id.categoria_escola);
        String bairro = null, municipio = null, cep = null, telefone = null, categoria = null;
        nome = null;

        mMapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapview);
        mMapFragment.getMapAsync(this);

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

        mMapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapview);
        mMapFragment.getMapAsync(this);


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.clear();
        mMap.getUiSettings().setZoomControlsEnabled(true); //Habilitando ZOOM no mapa
        mMap.setOnMapClickListener(this);

        LatLng escola = new LatLng(lati,longi);
        Log.e("LOCALIZAÇÃO","Lat: "+lati+" Longi: "+longi);

        mMap.addMarker(new MarkerOptions().position(escola).title(nome));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(escola,17));
    }

    @Override
    public void onMapClick(LatLng latLng) {

    }
}
