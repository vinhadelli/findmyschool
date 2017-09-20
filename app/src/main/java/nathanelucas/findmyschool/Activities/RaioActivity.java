package nathanelucas.findmyschool.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import nathanelucas.findmyschool.MyAdapter;
import nathanelucas.findmyschool.R;
import nathanelucas.findmyschool.Resposta_API.Escola;
import nathanelucas.findmyschool.RetrofitService;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RaioActivity extends AppCompatActivity {

    private float raio;
    LocationManager locationManager;
    private double late, longi;
    EditText raio_field;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raio);

        raio_field = (EditText) findViewById(R.id.numero_raio);

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        //getLastLocation();
        getLocation();

    }

    public void buscarMapa(View view)
    {
        raio = Float.valueOf(raio_field.getText().toString());
        Intent i = new Intent(getApplicationContext(), MapaActivity.class);

        i.putExtra("raio",raio);
        i.putExtra("late",late);
        i.putExtra("longi",longi);

        startActivity(i);
    }

    private void getLastLocation() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ) {
            Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            late = lastKnownLocation.getLatitude();
            longi = lastKnownLocation.getLongitude();
        }
    }

    private void getLocation() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ) {
            LocationListener locationListener = new LocationListener() {
                public void onLocationChanged(Location location) {
                    LatLng me = new LatLng(location.getLatitude(), location.getLongitude());
                    late = location.getLatitude();
                    longi = location.getLongitude();
                }

                public void onStatusChanged(String provider, int status, Bundle extras) {
                }

                public void onProviderEnabled(String provider) {
                }

                public void onProviderDisabled(String provider) {
                }
            };
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
        }
    }
}
