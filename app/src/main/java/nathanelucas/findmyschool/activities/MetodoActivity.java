package nathanelucas.findmyschool.activities;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import nathanelucas.findmyschool.R;

public class MetodoActivity extends AppCompatActivity {

    public static final int MAP_PERMISSIONS = 9999;
    private final Context mContext= this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metodo);


    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    public void passarParaLista(View view) {
        startActivity(new Intent(MetodoActivity.this, BuscaActivity.class));
    }

    public void passarParaMapa(View view) {

        if (checkPermissions() && checkGps()) {

            startActivity(new Intent(this, BigMapActivity.class));
        }

    }

    private boolean checkPermissions(){

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            askPermissions();
            return false;
        }
        return true;
    }

    private boolean checkGps(){

        LocationManager locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);

        if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            return true;
        }

        final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        final String action = Settings.ACTION_LOCATION_SOURCE_SETTINGS;
        final String message = "Habilite o GPS para utilizar essa função";

        builder.setMessage(message).setPositiveButton("OK", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface d, int id) {
                mContext.startActivity(new Intent(action));
                d.dismiss();
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface d, int id) {
                d.cancel();
            }
        });
        builder.create().show();

        return false;
    }

    private void askPermissions(){

            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    MAP_PERMISSIONS);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MAP_PERMISSIONS: {
                if (grantResults.length > 0) {

                    for(int grantResult : grantResults){
                        if (grantResult == PackageManager.PERMISSION_DENIED){
                            Toast.makeText(this, "É necessario ter permissões para essa operação", Toast.LENGTH_LONG).show();
                            return;
                        }
                    }
                    startActivity(new Intent(this, BigMapActivity.class));
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.activity_metodo_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(this, LoginActivity.class));
        finish();

        return super.onOptionsItemSelected(item);
    }
}
