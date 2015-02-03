package br.ueg.unucet.gymsys.Model;

import java.math.BigInteger;
import java.util.Date;

import br.ueg.unucet.gymsys.Anotations.Campo;
import br.ueg.unucet.gymsys.Anotations.Table;


@Table(nome="aluno")
public class Aluno extends Model<Integer>{

	@Campo(nome="idaluno", pk=true)
	private int idaluno;
	
	@Campo(nome="nome",obrigatorio=true)
	private String nome;
	
	@Campo(nome="data")
	private Date data;

	@Campo(nome="cpf",obrigatorio=true)
	private String cpf;
	
	@Campo(nome="datanascimento",obrigatorio=false)
	private Date datanascimento;
	
	@Campo(nome="celular",obrigatorio=false)
	private String celular;
	
	
	@Campo(nome="peso",obrigatorio=false)
	private Double peso;
	
	@Campo(nome="altura",obrigatorio=false)
	private Double altura;
	
	@Campo(nome="cintura",obrigatorio=false)
	private Double cintura;
	
	@Campo(nome="antebraco",obrigatorio=false)
	private Double antebraco;
	
	@Campo(nome="torax",obrigatorio=false)
	private Double torax;
	 
	@Campo(nome="abdomen",obrigatorio=false)
	private Double abdomen;
	
	@Campo(nome="coxa",obrigatorio=false)
	private Double coxa;
	
	@Campo(nome="pescoco",obrigatorio=false)
	private Double pescoco;
	
	@Campo(nome="ombro",obrigatorio=false)
	private Double ombro;
	
	@Campo(nome="ativo",obrigatorio=false)
	private boolean ativo;
	
	@Campo(nome="idusuario",obrigatorio=false)
	private Usuario usuario;

	public Aluno() {
	}


	public int getIdaluno() {
		return idaluno;
	}
	public void setIdaluno(int idaluno) {
		this.idaluno = idaluno;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDatanascimento() {
		return datanascimento;
	}

	public void setDatanascimento(Date datanascimento) {
		this.datanascimento = datanascimento;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}


	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public Double getAltura() {
		return altura;
	}

	public void setAltura(Double altura) {
		this.altura = altura;
	}

	public Double getCintura() {
		return cintura;
	}

	public void setCintura(Double cintura) {
		this.cintura = cintura;
	}

	public Double getAntebraco() {
		return antebraco;
	}

	public void setAntebraco(Double antebraco) {
		this.antebraco = antebraco;
	}

	public Double getTorax() {
		return torax;
	}

	public void setTorax(Double torax) {
		this.torax = torax;
	}

	public Double getAbdomen() {
		return abdomen;
	}

	public void setAbdomen(Double abdomen) {
		this.abdomen = abdomen;
	}

	public Double egetCoxa() {
		return coxa;
	}

	public void setCoxa(Double coxa) {
		this.coxa = coxa;
	}

	public Double getPescoco() {
		return pescoco;
	}

	public void setPescoco(Double pescoco) {
		this.pescoco = pescoco;
	}

	public Double getOmbro() {
		return ombro;
	}

	public void setOmbro(Double ombro) {
		this.ombro = ombro;
	}

	public String getTabelaNome() {
		return "aluno";
	}
	public String getVariaveisI() {
		return "nome, cpf, datanascimento, celular,"
				+ " altura, peso, cintura, antebraco, torax, abdomen, pescoco,"
				+ "coxa, ombro, data, ativo";
	}
	  
	public String getatributosI() {
		String atributos = "'"+ this.nome+ "',";
		/*atributos = atributos + "'"+ Criptografia.criptografar(this.senha) + "',";
		atributos = atributos + " "+ this.tipousuario + " ,";*/
		atributos = atributos + " "+ this.cpf + " ,";
		atributos = atributos + " "+ this.datanascimento + " ,";
		atributos = atributos + " "+ this.celular + " ,";
		atributos = atributos + " "+ this.altura + " ,";
		atributos = atributos + " "+ this.peso + " ,";
		atributos = atributos + " "+ this.cintura + " ,";
		atributos = atributos + " "+ this.antebraco + " ,";
		atributos = atributos + " "+ this.torax + " ,";
		atributos = atributos + " "+ this.abdomen + " ,";
		atributos = atributos + " "+ this.pescoco + " ,";
		atributos = atributos + " "+ this.coxa + " ,";
		atributos = atributos + " "+ this.ombro + " ,";
		/*if(this.ativo){
			atributos = atributos + " "+ "true" + " ";
		} else{
			atributos = atributos + " "+ "false" + " ";
		}*/
		atributos = atributos + " " + this.data + " ,";
		atributos = atributos + " " + this.ativo + " ,";
		return atributos;
	}
	
	@Override
	public String getVariaveisPesquisarNome() {
		return "nome";
	}
	
	public int getId() {
		return this.idaluno;
	}


	@Override
	public boolean getAtivo() {
		return this.ativo;
	}

	public Date getData() {
		return data;
	}


	public void setData(Date data) {
		this.data = data;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	public String getCriterio() {
		return "idusuario";
	}


	public String getOrdenacao() {
		return "nome";
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public Double getCoxa() {
		return coxa;
	}



	
}

