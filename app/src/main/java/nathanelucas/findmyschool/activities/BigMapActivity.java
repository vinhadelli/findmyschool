package nathanelucas.findmyschool.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nathanelucas.findmyschool.R;
import nathanelucas.findmyschool.RetrofitService;
import nathanelucas.findmyschool.resposta_api.Escola;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BigMapActivity extends AppCompatActivity implements OnMapReadyCallback{

    private GoogleMap mMap;
    LocationManager locationManager;

    private Map<String, Escola> schools = new HashMap<>();
    private LatLng user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_full);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.clear();
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                if(marker.getTitle().equals("Eu")){
                    return false;
                }

                Escola school = schools.get(marker.getTitle());
                Intent intent = new Intent(BigMapActivity.this, EscolaActivity.class);

                intent.putExtra("nome", school.getNome());
                intent.putExtra("bairro", school.getEndereco().getBairro());
                intent.putExtra("municipio", school.getEndereco().getMunicipio());
                intent.putExtra("cep", school.getEndereco().getCep());
                intent.putExtra("telefone", school.getTelefone());
                intent.putExtra("categoria", school.getCategoriaEscolaPrivada());
                intent.putExtra("lati",school.getLatitude());
                intent.putExtra("longi",school.getLongitude());

                startActivity(intent);

                return true;
            }
        });

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        pointUserOnMap();

    }

    private void pointUserOnMap(){

        locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            user = new LatLng(location.getLatitude(), location.getLongitude());
                    mMap.addMarker(new MarkerOptions().position(user).title("Eu").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(user, 15));
            pointSchoolsOnMap();
        }

    }

    private void pointSchoolsOnMap(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitService.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();

        RetrofitService service = retrofit.create(RetrofitService.class);

        Call<List<Escola>> getEscolas = service.getEscolasPorLocalizacao(user.latitude, user.longitude, 100);

        getEscolas.enqueue(new retrofit2.Callback<List<Escola>>() {
            @Override
            public void onResponse(Call<List<Escola>> call, retrofit2.Response<List<Escola>> response)
            {
                if(response.isSuccessful()){
                    for (Escola escola : response.body()){
                        schools.put(escola.getNome(), escola);
                        LatLng localizacao = new LatLng(escola.getLatitude(),escola.getLongitude());
                        mMap.addMarker(new MarkerOptions().position(localizacao).title(escola.getNome()));
                    }
                }
                else {
                    Toast.makeText(BigMapActivity.this, "Não foi possivel resgatar os dados!", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<Escola>> call, Throwable t){
                Toast.makeText(BigMapActivity.this, "A conexão falhou!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
