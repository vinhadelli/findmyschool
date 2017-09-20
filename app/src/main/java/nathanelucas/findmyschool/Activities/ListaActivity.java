package nathanelucas.findmyschool.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

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


public class ListaActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RetrofitService service;
    private ProgressBar progressBar;
    private String creche;
    private String fundamental;
    private String medio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        progressBar = (ProgressBar) findViewById(R.id.progress_api);

        //String nome = null;
        String estado = null;


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            //nome = extras.getString("nome");
            estado = extras.getString("estado");
            creche = extras.getString("creche");
            fundamental = extras.getString("fundamental");
            medio = extras.getString("medio");
            //service = (RetrofitService) extras.get("retrofit");
            //The key argument here must match that used in the other activity
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitService.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                //.client(httpClient.build())
                .build();

        service = retrofit.create(RetrofitService.class);

        Call<List<Escola>> getEscolas = service.getEscolas(estado, 700);

        getEscolas.enqueue(new retrofit2.Callback<List<Escola>>() {
        @Override
        public void onResponse(Call<List<Escola>> call, retrofit2.Response<List<Escola>> response)
        {
            progressBar.setVisibility(View.VISIBLE);
            if(response.isSuccessful()){
                List<Escola> e = new ArrayList<Escola>();
                if (Objects.equals(creche, "S") && Objects.equals(fundamental, "S") && Objects.equals(medio, "S"))
                {
                    for (Escola escola : response.body()){
                        //Log.e("RESPSOTA","Nome: "+escola.getNome()+" Creche: "+ escola.getInfraestrutura().getTemCreche()+ creche+" Fundamental: "+escola.getInfraestrutura().getTemEnsinoFundamental()+fundamental+" Medio: "+escola.getInfraestrutura().getTemEnsinoMedio()+medio);
                        if(Objects.equals(escola.getInfraestrutura().getTemCreche(), creche) && Objects.equals(escola.getInfraestrutura().getTemEnsinoFundamental(), fundamental) && Objects.equals(escola.getInfraestrutura().getTemEnsinoMedio(), medio))
                            e.add(escola);
                    }
                }
                else if (Objects.equals(creche, "S") && Objects.equals(fundamental, "S"))
                {
                    for (Escola escola : response.body()){
                        //Log.e("RESPSOTA","Nome: "+escola.getNome()+" Creche: "+ escola.getInfraestrutura().getTemCreche()+ creche+" Fundamental: "+escola.getInfraestrutura().getTemEnsinoFundamental()+fundamental+" Medio: "+escola.getInfraestrutura().getTemEnsinoMedio()+medio);
                        if(Objects.equals(escola.getInfraestrutura().getTemCreche(), creche) && Objects.equals(escola.getInfraestrutura().getTemEnsinoFundamental(), fundamental))
                            e.add(escola);
                    }
                }
                else if (Objects.equals(medio, "S") && Objects.equals(fundamental, "S"))
                {
                    for (Escola escola : response.body()){
                        //Log.e("RESPSOTA","Nome: "+escola.getNome()+" Creche: "+ escola.getInfraestrutura().getTemCreche()+ creche+" Fundamental: "+escola.getInfraestrutura().getTemEnsinoFundamental()+fundamental+" Medio: "+escola.getInfraestrutura().getTemEnsinoMedio()+medio);
                        if(Objects.equals(escola.getInfraestrutura().getTemEnsinoFundamental(), fundamental) && Objects.equals(escola.getInfraestrutura().getTemEnsinoMedio(), medio))
                            e.add(escola);
                    }
                }
                else if (Objects.equals(creche, "S") && Objects.equals(medio, "S"))
                {
                    for (Escola escola : response.body()){
                        //Log.e("RESPSOTA","Nome: "+escola.getNome()+" Creche: "+ escola.getInfraestrutura().getTemCreche()+ creche+" Fundamental: "+escola.getInfraestrutura().getTemEnsinoFundamental()+fundamental+" Medio: "+escola.getInfraestrutura().getTemEnsinoMedio()+medio);
                        if(Objects.equals(escola.getInfraestrutura().getTemCreche(), creche) && Objects.equals(escola.getInfraestrutura().getTemEnsinoMedio(), medio))
                            e.add(escola);
                    }
                }
                else{
                for (Escola escola : response.body()){
                    //Log.e("RESPSOTA","Nome: "+escola.getNome()+" Creche: "+ escola.getInfraestrutura().getTemCreche()+ creche+" Fundamental: "+escola.getInfraestrutura().getTemEnsinoFundamental()+fundamental+" Medio: "+escola.getInfraestrutura().getTemEnsinoMedio()+medio);
                    if(Objects.equals(escola.getInfraestrutura().getTemCreche(), creche) || Objects.equals(escola.getInfraestrutura().getTemEnsinoFundamental(), fundamental) || Objects.equals(escola.getInfraestrutura().getTemEnsinoMedio(), medio))
                        e.add(escola);
                }}
                progressBar.setVisibility(View.GONE);
                recyclerView.setAdapter(new MyAdapter(e));

                Log.e("RESPOSTA","Esta no onResponse!");
            }
            else {
                Log.e("RESPOSTA","ERRO esta no else");
                Toast.makeText(ListaActivity.this, "Não foi possivel resgatar os dados!",
                        Toast.LENGTH_SHORT).show();
            }
        }
                @Override
            public void onFailure(Call<List<Escola>> call, Throwable t){
                    Log.e("RESPOSTA","Esta no onFailure");
                    Toast.makeText(ListaActivity.this, "A conexão falhou!",
                            Toast.LENGTH_SHORT).show();
            }
        });
    }
}
