package nathanelucas.findmyschool;

/**
 * Created by nathan.vinhadelli on 30/08/2017.
 */

public class RespostaServidor {
    private String categoriaEscolaPrivada;
    private String cnpj;
    private int codEscola;
    private String email;
    private String esferaAdministrativa;
    private Endereco endereco;
    private Infraestrutura infraestrutura;
    private Links links;
    private float latitude;
    private float longitude;
    private String nome;
    private int qtdAlunos;
    private int qtdComputadores;
    private int qtdComputadoresPorAluno;
    private int qtdFuncionarios;
    private int qtdSalasExistentes;
    private int qtdSalasUtilizadas;
    private String rede;
    private String seConveniadaSetorPublico;
    private String seFimLucrativo;
    private String situacaoFuncionamento;
    private String telefone;
    private String tipoConvenioPoderPublico;
    private String urlWebSite;
    private String zona;
    

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

	public void setEsferaAdministrativa(String esferaAdministrativa) {
		this.esferaAdministrativa = esferaAdministrativa;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public void setInfraestrutura(Infraestrutura infraestrutura) {
		this.infraestrutura = infraestrutura;
	}

	public void setLinks(Links links) {
		this.links = links;
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

	public void setQtdComputadoresPorAluno(int qtdComputadoresPorAluno) {
		this.qtdComputadoresPorAluno = qtdComputadoresPorAluno;
	}

	public void setQtdFuncionarios(int qtdFuncionarios) {
		this.qtdFuncionarios = qtdFuncionarios;
	}

	public void setQtdSalasExistentes(int qtdSalasExistentes) {
		this.qtdSalasExistentes = qtdSalasExistentes;
	}

	public void setQtdSalasUtilizadas(int qtdSalasUtilizadas) {
		this.qtdSalasUtilizadas = qtdSalasUtilizadas;
	}

	public void setRede(String rede) {
		this.rede = rede;
	}

	public void setSeConveniadaSetorPublico(String seConveniadaSetorPublico) {
		this.seConveniadaSetorPublico = seConveniadaSetorPublico;
	}

	public void setSeFimLucrativo(String seFimLucrativo) {
		this.seFimLucrativo = seFimLucrativo;
	}

	public void setSituacaoFuncionamento(String situacaoFuncionamento) {
		this.situacaoFuncionamento = situacaoFuncionamento;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public void setTipoConvenioPoderPublico(String tipoConvenioPoderPublico) {
		this.tipoConvenioPoderPublico = tipoConvenioPoderPublico;
	}

	public void setUrlWebSite(String urlWebSite) {
		this.urlWebSite = urlWebSite;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public RespostaServidor(){}

    public String getCategoriaEscolaPrivada(){return categoriaEscolaPrivada;}
    public String getCnpj(){return cnpj;}
    public String getEmail(){return email;}
    public String getEsferaAdministrativa(){return esferaAdministrativa;}
    public String getNome(){return nome;}
    public String getRede(){return rede;}
    public String getSeConveniadaSetorPublico(){return seConveniadaSetorPublico;}
    public String getSeFimLucrativo(){return seFimLucrativo;}
    public String getSituacaoFuncionamento(){return situacaoFuncionamento;}
    public String getTelefone(){return telefone;}
    public String getTipoConvenioPoderPublico(){return tipoConvenioPoderPublico;}
    public String getUrlWebSite(){return urlWebSite;}
    public String getZona(){return zona;}
    public int getCodEscola(){return codEscola;}
    public int getQtdAlunos(){return qtdAlunos;}
    public Endereco getEndereco() {
		return endereco;
	}
    public Infraestrutura getInfraestrutura() {
		return infraestrutura;
	}
    public float getLatitude() {
		return latitude;
	}
    public Links getLinks() {
		return links;
	}
    public float getLongitude() {
		return longitude;
	}
    public int getQtdComputadores() {
		return qtdComputadores;
	}
    public int getQtdComputadoresPorAluno() {
		return qtdComputadoresPorAluno;
	}
    public int getQtdFuncionarios() {
		return qtdFuncionarios;
	}
    public int getQtdSalasExistentes() {
		return qtdSalasExistentes;
	}
    public int getQtdSalasUtilizadas() {
		return qtdSalasUtilizadas;
	}
    
    private class Endereco
    {
        private String bairro;
        private String cep;
        private String descricao;
        private String municipio;
        private String uf;
        
		public Endereco(String bairro, String cep, String descricao, String municipio, String uf) {
			super();
			this.bairro = bairro;
			this.cep = cep;
			this.descricao = descricao;
			this.municipio = municipio;
			this.uf = uf;
		}
    }

    private class Links
    {
        private String href;
        private String rel;
        private Boolean templated;
        
		public Links(String href, String rel, Boolean templated) {
			super();
			this.href = href;
			this.rel = rel;
			this.templated = templated;
		}      
    }

    private class Infraestrutura
    {
        private String atendeEducacaoEspecializada;
        private String banheiroTemChuveiro;
        private String ofereceAlimentacao;
        private String temAcessibilidade;
        private String temAguaFiltrada;
        private String temAlmoxarifado;
        private String temAreaVerde;
        private String temAuditorio;
        private String temBandaLarga;
        private String temBercario;
        private String temBiblioteca;
        private String temCozinha;
        private String temCreche;
        private String temDespensa;
        private String temEducacaoIndigena;
        private String temEducacaoJovemAdulto;
        private String temEnsinoFundamental;
        private String temEnsinoMedio;
        private String temEnsinoMedioIntegrado;
        private String temEnsinoMedioNormal;
        private String temEnsinoMedioProfissional;
        private String temInternet;
        private String temLaboratorioCiencias;
        private String temLaboratorioInformatica;
        private String temParqueInfantil;
        private String temPatioCoberto;
        private String temPatioDescoberto;
        private String temQuadraEsporteCoberta;
        private String temQuadraEsporteDescoberta;
        private String temRefeitorio;
        private String temSalaLeitura;
        private String temSanitarioForaPredio;
        private String temSanitarioNoPredio;
		public Infraestrutura(String atendeEducacaoEspecializada, String banheiroTemChuveiro, String ofereceAlimentacao,
				String temAcessibilidade, String temAguaFiltrada, String temAlmoxarifado, String temAreaVerde,
				String temAuditorio, String temBandaLarga, String temBercario, String temBiblioteca, String temCozinha,
				String temCreche, String temDespensa, String temEducacaoIndigena, String temEducacaoJovemAdulto,
				String temEnsinoFundamental, String temEnsinoMedio, String temEnsinoMedioIntegrado,
				String temEnsinoMedioNormal, String temEnsinoMedioProfissional, String temInternet,
				String temLaboratorioCiencias, String temLaboratorioInformatica, String temParqueInfantil,
				String temPatioCoberto, String temPatioDescoberto, String temQuadraEsporteCoberta,
				String temQuadraEsporteDescoberta, String temRefeitorio, String temSalaLeitura,
				String temSanitarioForaPredio, String temSanitarioNoPredio) {
			super();
			this.atendeEducacaoEspecializada = atendeEducacaoEspecializada;
			this.banheiroTemChuveiro = banheiroTemChuveiro;
			this.ofereceAlimentacao = ofereceAlimentacao;
			this.temAcessibilidade = temAcessibilidade;
			this.temAguaFiltrada = temAguaFiltrada;
			this.temAlmoxarifado = temAlmoxarifado;
			this.temAreaVerde = temAreaVerde;
			this.temAuditorio = temAuditorio;
			this.temBandaLarga = temBandaLarga;
			this.temBercario = temBercario;
			this.temBiblioteca = temBiblioteca;
			this.temCozinha = temCozinha;
			this.temCreche = temCreche;
			this.temDespensa = temDespensa;
			this.temEducacaoIndigena = temEducacaoIndigena;
			this.temEducacaoJovemAdulto = temEducacaoJovemAdulto;
			this.temEnsinoFundamental = temEnsinoFundamental;
			this.temEnsinoMedio = temEnsinoMedio;
			this.temEnsinoMedioIntegrado = temEnsinoMedioIntegrado;
			this.temEnsinoMedioNormal = temEnsinoMedioNormal;
			this.temEnsinoMedioProfissional = temEnsinoMedioProfissional;
			this.temInternet = temInternet;
			this.temLaboratorioCiencias = temLaboratorioCiencias;
			this.temLaboratorioInformatica = temLaboratorioInformatica;
			this.temParqueInfantil = temParqueInfantil;
			this.temPatioCoberto = temPatioCoberto;
			this.temPatioDescoberto = temPatioDescoberto;
			this.temQuadraEsporteCoberta = temQuadraEsporteCoberta;
			this.temQuadraEsporteDescoberta = temQuadraEsporteDescoberta;
			this.temRefeitorio = temRefeitorio;
			this.temSalaLeitura = temSalaLeitura;
			this.temSanitarioForaPredio = temSanitarioForaPredio;
			this.temSanitarioNoPredio = temSanitarioNoPredio;
		}
    }
}
