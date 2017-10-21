package nathanelucas.findmyschool;

import java.util.List;

import nathanelucas.findmyschool.resposta_api.Escola;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by nathan.vinhadelli on 04/09/2017.
 */

public interface RetrofitService {

    String API_BASE_URL = "http://mobile-aceite.tcu.gov.br:80/nossaEscolaRS/";

    @GET("rest/escolas")
    Call<List<Escola>> getEscolas();

    @GET("rest/escolas")
    Call<List<Escola>> getEscolas(@Query("nome") String nome);

    @GET("rest/escolas")
    Call<List<Escola>> getEscolas(@Query("nome") String nome, @Query("uf") String uf,@Query("quantidadeDeItens") int quantidadeDeItens);

    @GET("rest/escolas")
    Call<List<Escola>> getEscolas(@Query("uf") String uf,@Query("quantidadeDeItens") int quantidadeDeItens);

    @GET ("rest/escolas/{codEscola}/avaliacoes")
    Call<List<Escola>> getAvaliacoes(@Path("codEscola") int codEscola);

    @GET ("rest/escolas/latitude/{latitude}/longitude/{longitude}/raio/{raio}")
    Call<List<Escola>> getEscolasPorLocalizacao(@Path("latitude") double latitude, @Path("longitude") double longitude, @Path("raio") float raio);
}
