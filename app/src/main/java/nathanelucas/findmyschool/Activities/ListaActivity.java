package nathanelucas.findmyschool.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import nathanelucas.findmyschool.MyAdapter;
import nathanelucas.findmyschool.R;
import nathanelucas.findmyschool.Resposta_API.Escola;
import nathanelucas.findmyschool.RetrofitService;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ListaActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RetrofitService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        String nome = null;
        String estado = null;

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            nome = extras.getString("nome");
            estado = extras.getString("estado");
            //service = (RetrofitService) extras.get("retrofit");
            //The key argument here must match that used in the other activity
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitService.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                //.client(httpClient.build())
                .build();

        service = retrofit.create(RetrofitService.class);

        Call<List<Escola>> getEscolas = service.getEscolas(nome, estado);

        getEscolas.enqueue(new retrofit2.Callback<List<Escola>>() {
        @Override
        public void onResponse(Call<List<Escola>> call, retrofit2.Response<List<Escola>> response)
        {
            if(response.isSuccessful()){
                List<Escola> e = new ArrayList<Escola>();
                for (Escola escola : response.body()){
                    e.add(escola);
                }
                recyclerView.setAdapter(new MyAdapter(e));

                Log.e("RESPOSTA","Esta no onResponse!");
            }
            else {
                Log.e("RESPOSTA","ERRO esta no else");
            }
        }
                @Override
            public void onFailure(Call<List<Escola>> call, Throwable t){
                    Log.e("RESPOSTA","Esta no onFailure");
            }
        });
    }
}
