package nathanelucas.findmyschool.Resposta_API;

/**
 * Created by nathan.vinhadelli on 30/08/2017.
 */

public class Escola{
    private String categoriaEscolaPrivada;
    private String cnpj;
    private int codEscola;
    private String email;
    private Endereco endereco;
    private Infraestrutura infraestrutura;
    private float latitude;
    private float longitude;
    private String nome;
    private int qtdAlunos;
    private int qtdComputadores;
    private int qtdFuncionarios;
    private int qtdSalasUtilizadas;
    private String rede;
    private String situacaoFuncionamento;
    private String telefone;

	private int mData;


	public void setCategoriaEscolaPrivada(String categoriaEscolaPrivada) {
		this.categoriaEscolaPrivada = categoriaEscolaPrivada;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public void setCodEscola(int codEscola) {
		this.codEscola = codEscola;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public Infraestrutura getInfraestrutura() {
		return infraestrutura;
	}

	public void setInfraestrutura(Infraestrutura infraestrutura) {
		this.infraestrutura = infraestrutura;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setQtdAlunos(int qtdAlunos) {
		this.qtdAlunos = qtdAlunos;
	}

	public void setQtdComputadores(int qtdComputadores) {
		this.qtdComputadores = qtdComputadores;
	}

	public void setQtdFuncionarios(int qtdFuncionarios) {
		this.qtdFuncionarios = qtdFuncionarios;
	}

	public void setQtdSalasUtilizadas(int qtdSalasUtilizadas) {
		this.qtdSalasUtilizadas = qtdSalasUtilizadas;
	}
	public void setRede(String rede) {
		this.rede = rede;
	}

	public void setSituacaoFuncionamento(String situacaoFuncionamento) {
		this.situacaoFuncionamento = situacaoFuncionamento;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Escola(){}

    public String getCategoriaEscolaPrivada(){return categoriaEscolaPrivada;}
    public String getCnpj(){return cnpj;}
    public String getEmail(){return email;}
    public String getNome(){return nome;}
    public String getRede(){return rede;}
    public String getSituacaoFuncionamento(){return situacaoFuncionamento;}
    public String getTelefone(){return telefone;}
    public int getCodEscola(){return codEscola;}
    public int getQtdAlunos(){return qtdAlunos;}
    public float getLatitude() {
		return latitude;
	}
    public float getLongitude() {
		return longitude;
	}
    public int getQtdComputadores() {
		return qtdComputadores;
	}
    public int getQtdFuncionarios() {
		return qtdFuncionarios;
	}
    public int getQtdSalasUtilizadas() {
		return qtdSalasUtilizadas;
	}

}
