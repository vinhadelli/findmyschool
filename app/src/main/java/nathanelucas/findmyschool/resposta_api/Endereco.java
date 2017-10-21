package nathanelucas.findmyschool.resposta_api;

/**
 * Created by nathan.vinhadelli on 04/09/2017.
 */

public class Endereco
{
    private String bairro;
    private String cep;
    private String municipio;
    private String uf;

    public Endereco() {
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
}
