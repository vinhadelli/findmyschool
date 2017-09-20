package nathanelucas.findmyschool.Activities;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import nathanelucas.findmyschool.R;
import nathanelucas.findmyschool.Resposta_API.Escola;
import nathanelucas.findmyschool.RetrofitService;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MapaActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener {
    private GoogleMap mMap;
    LocationManager locationManager;
    private RetrofitService service;
    private float raio;
    private double late, longi;
    private List<Escola> escolas;
//    ProgressBar progressBar;


    public static final int MAP_PERMISSION_ACCESS_FINE_LOCATION = 9999;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        escolas = new ArrayList<>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        Bundle extras = getIntent().getExtras();
        raio = extras.getFloat("raio");
        late = extras.getDouble("late");
        longi = extras.getDouble("longi");
//        progressBar = (ProgressBar) findViewById(R.id.progress_mapa);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitService.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                //.client(httpClient.build())
                .build();

        service = retrofit.create(RetrofitService.class);

        Call<List<Escola>> getEscolas = service.getEscolasPorLocalizacao(late, longi, raio, 100);

        getEscolas.enqueue(new retrofit2.Callback<List<Escola>>() {
            @Override
            public void onResponse(Call<List<Escola>> call, retrofit2.Response<List<Escola>> response)
            {
                if(response.isSuccessful()){
                    for (Escola escola : response.body()){
                        escolas.add(escola);
//                        LatLng localizacao = new LatLng(escola.getLatitude(),escola.getLongitude());
//                        mMap.addMarker(new MarkerOptions().position(localizacao).title(escola.getNome()));
                    }

                    Log.e("RESPOSTA","Esta no onResponse!");
                }
                else {
                    Log.e("RESPOSTA","ERRO esta no else");
                    Toast.makeText(MapaActivity.this, "Não foi possivel resgatar os dados!",
                            Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<Escola>> call, Throwable t){
                Log.e("RESPOSTA","Esta no onFailure");
                Toast.makeText(MapaActivity.this, "A conexão falhou!",
                        Toast.LENGTH_SHORT).show();
            }
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapa_full);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
//        progressBar.setVisibility(View.VISIBLE);

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        mMap.getUiSettings().setZoomControlsEnabled(true); //Habilitando ZOOM no mapa
        mMap.setOnMapClickListener(this);
        Log.e("LOCALIZAÇÃO","Lat: "+late+" Longi: "+longi);

        for (Escola escola : escolas)
        {
            LatLng localizacao = new LatLng(escola.getLatitude(),escola.getLongitude());
            mMap.addMarker(new MarkerOptions().position(localizacao).title(escola.getNome()));
            Log.e("MARCADORES","Nome:"+escola.getNome()+"Lat: "+escola.getLatitude()+" Longi: "+escola.getLongitude());
        }

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions( this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, MAP_PERMISSION_ACCESS_FINE_LOCATION);
        } else {
            getLastLocation();
            getLocation();
        }


//        progressBar.setVisibility(View.GONE);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(late,longi),14));
    }

    private void getLastLocation() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ) {
            Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            LatLng me = new LatLng(lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude());
            late = lastKnownLocation.getLatitude();
            longi = lastKnownLocation.getLongitude();
            mMap.addMarker(new MarkerOptions().position(me).title("Eu estava aqui quando o android me localizou pela última vez!!!"));
//            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(me, 10));
            Log.e("LOCALIZAÇÃO","Lat: "+late+" Longi: "+longi);
        }
    }

    private void getLocation() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ) {
            LocationListener locationListener = new LocationListener() {
                public void onLocationChanged(Location location) {
                    LatLng me = new LatLng(location.getLatitude(), location.getLongitude());
                    late = location.getLatitude();
                    longi = location.getLongitude();
                    mMap.addMarker(new MarkerOptions().position(me).title("Estou Aqui!!!"));
//                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(me, 10));
                }

                public void onStatusChanged(String provider, int status, Bundle extras) {
                }

                public void onProviderEnabled(String provider) {
                }

                public void onProviderDisabled(String provider) {
                }
            };
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, locationListener);
            Log.e("LOCALIZAÇÃO","Lat: "+late+" Longi: "+longi);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MAP_PERMISSION_ACCESS_FINE_LOCATION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getLastLocation();
                    getLocation();
                } else {
                    //Permissão negada
                }
                return;
            }
        }
    }

    @Override
    public void onMapClick(LatLng latLng) {

    }
}
