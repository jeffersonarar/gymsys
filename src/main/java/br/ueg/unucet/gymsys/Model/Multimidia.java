package br.ueg.unucet.gymsys.Model;

import java.util.Date;

import br.ueg.unucet.gymsys.Anotations.Campo;
import br.ueg.unucet.gymsys.Anotations.Table;


@Table(nome="multimidia")
public class Multimidia extends Model<Integer> {

	@Campo(nome="idmultimidia", pk=true)
	private int idmultimidia;
	
	@Campo(nome="nome")
	private String nome;
	
	@Campo(nome="descricao")
	private String descricao;
	
	@Campo(nome="multimidia")
	private String multimidia;
	
	@Campo(nome="ativo",obrigatorio=true)
	private boolean ativo;
	
	@Campo(nome="idexercicio")
	private Exercicio exercicio;
	
	@Campo(nome="idcategoria")
	private Categoria categoria;
	
	@Campo(nome="idaluno")
	private Aluno aluno;
	
	@Campo(nome="tipomultimidia")
	private boolean tipomultimidia;

	@Campo(nome="datainsercao")
	private Date datainsercao;
	
	public Multimidia(Object object, String nome, String descricao,
			String multimidia , Exercicio exercicio, Categoria categoria, Aluno aluno, boolean tipomultimidia, boolean ativo) {
		this.idmultimidia = (Integer) object;
		this.nome = nome;
		this.descricao = descricao;
		this.multimidia =  multimidia;
		this.exercicio = exercicio;
		this.categoria = categoria;
		this.aluno = aluno;
		this.tipomultimidia = tipomultimidia;
		this.ativo = ativo;
	}

	public Multimidia() {
	}

	public String getTabelaNome() {
		return "multimidia";
	}
	public String getVariaveisI() {
		return "nome, descricao, multimidia, exercicio, categoria, aluno, tipomultimidia, ativo, datainsercao";
	}
	public String getatributosI() {
		String atributos = "'"+ this.nome+ "',";
		atributos = atributos + " "+ this.descricao + " ,";
		atributos = atributos + " "+ this.multimidia + " ,";
		atributos = atributos + " "+ this.exercicio + " ,";
		atributos = atributos + " " + this.categoria + " ,";
		atributos = atributos + " " + this.aluno + " ,";
		if(this.tipomultimidia){
			atributos = atributos + " "+ "true" + " ";
		} else{
			atributos = atributos + " "+ "false" + " ";
		}
		if(this.ativo){
			atributos = atributos + " "+ "true" + " ";
		} else{
			atributos = atributos + " "+ "false" + " ";
		}
		atributos = atributos + " " + this.datainsercao + " ,";
		return atributos;
	}
	
	@Override
	public String getVariaveisPesquisarNome() {
		return "nome";
	}

	public int getIdmultimidia() {
		return idmultimidia;
	}

	public void setIdmultimidia(int idmultimidia) {
		this.idmultimidia = idmultimidia;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	public String getMultimidia() {
		return multimidia;
	}

	public void setMultimidia(String multimidia) {
		this.multimidia = multimidia;
	}


	public Exercicio getExercicio() {
		return exercicio;
	}

	public void setExercicio(Exercicio exercicio) {
		this.exercicio = exercicio;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public boolean getTipomultimidia() {
		return tipomultimidia;
	}

	public void setTipomultimidia(boolean tipomultimidia) {
		this.tipomultimidia = tipomultimidia;
	}
	
	public Date getDatainsercao() {
		return datainsercao;
	}

	public void setDatainsercao(Date datainsercao) {
		this.datainsercao = datainsercao;
	}

	public String getCriterio() {
		return "idexercicio";
	}

	public String getOrdenacao() {
		return "idmultimidia";
	}

}
