package nathanelucas.findmyschool.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metodo);


    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    public void passarParaLista(View view)
    {
        startActivity(new Intent(MetodoActivity.this, BuscaActivity.class));
    }

    public void passarParaMapa(View view)
    {

        if (checkPermissions()) {

            startActivity(new Intent(this, BigMapActivity.class));
        }
        else{

            askPermissions();
        }

    }

    private boolean checkPermissions(){

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            return false;
        }
        return true;
    }

    public void askPermissions(){

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
