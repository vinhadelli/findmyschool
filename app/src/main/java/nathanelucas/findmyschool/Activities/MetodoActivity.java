package nathanelucas.findmyschool.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import nathanelucas.findmyschool.R;

public class MetodoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metodo);
    }

    public void passarParaLista(View view)
    {
        startActivity(new Intent(MetodoActivity.this, BuscaActivity.class));
    }

    public void passarParaMapa(View view)
    {
        //startActivity(new Intent(MetodoActivity.this, MapaActivity.class));
    }
}
