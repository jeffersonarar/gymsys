package br.ueg.unucet.gymsys.Model;

import java.util.Date;

import br.ueg.unucet.gymsys.Anotations.Campo;
import br.ueg.unucet.gymsys.Anotations.Table;

@Table(nome="fichaTreino")
public class FichaTreino  extends Model<Integer>{
	
	@Campo(nome="idfichatreino", pk=true)
	private int idfichatreino;

	@Campo(nome="idaluno",obrigatorio=true)
	private Aluno aluno;
	
	@Campo(nome="idcategoria",obrigatorio=true)
	private Categoria categoria;
	
	@Campo(nome="idexercicio",obrigatorio=true)
	private Exercicio exercicio;
	
	@Campo(nome="semana",obrigatorio=true)
	private String semana;
	
	@Campo(nome="serie",obrigatorio=true)
	private Integer serie;
	
	@Campo(nome="repeticao",obrigatorio=true)
	private String repeticao;
	
	@Campo(nome="ativo",obrigatorio=true)
	private boolean ativo;
	
	@Campo(nome="datainsercao",obrigatorio=true)
	private Date datainsercao;
	

	@Override
	public String getVariaveisPesquisarNome() {
		return "repeticao";
	}


	public String getTabelaNome() {
		return "fichaExercicio";
	}
	
	public String getVariaveisI() {
		return "repeticao, exercicio, categoria, aluno, semana, serie, ativo";
	}
	
	
	public FichaTreino(){
		
	}


	public FichaTreino(Object idfichatreino, Aluno aluno, Categoria categoria,
			Exercicio exercicio, String semana, Integer serie, String repeticao,
			boolean ativo) {
		this.idfichatreino = (Integer) idfichatreino;
		this.aluno = aluno;
		this.categoria = categoria;
		this.exercicio = exercicio;
		this.semana = semana;
		this.serie =  serie;
		this.repeticao = repeticao;
		this.ativo = ativo;
	}


	public String getatributosI() {
		String atributos = "'"+ this.repeticao+ "',";
		atributos = atributos + " " + this.serie + " ,";
		atributos = atributos + " " + this.semana + " ,";
		atributos = atributos + " "+ this.exercicio + " ,";
		atributos = atributos + " " + this.categoria + " ,";
		atributos = atributos + " " + this.aluno + " ,";
		if(this.ativo){
			atributos = atributos + " "+ "true" + " ";
		} else{
			atributos = atributos + " "+ "false" + " ";
		}
		return atributos;
	}
	
	@Override
	public boolean getAtivo() {
		return ativo;
	}

	public int getIdfichatreino() {
		return idfichatreino;
	}


	public void setIdfichatreino(int idfichatreino) {
		this.idfichatreino = idfichatreino;
	}


	public Aluno getAluno() {
		return aluno;
	}


	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}


	public Categoria getCategoria() {
		return categoria;
	}


	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}


	public Exercicio getExercicio() {
		return exercicio;
	}


	public void setExercicio(Exercicio exercicio) {
		this.exercicio = exercicio;
	}

	

	public String getSemana() {
		return semana;
	}


	public void setSemana(String semana) {
		this.semana = semana;
	}


	public Integer getSerie() {
		return serie;
	}


	public void setSerie(Integer serie) {
		this.serie = serie;
	}


	public String getRepeticao() {
		return repeticao;
	}


	public void setRepeticao(String repeticao) {
		this.repeticao = repeticao;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Date getDatainsercao() {
		return datainsercao;
	}


	public void setDatainsercao(Date datainsercao) {
		this.datainsercao = datainsercao;
	}


	public String getCriterio() {
		return "idaluno";
	}


	public String getOrdenacao() {
		return " CASE WHEN semana='Segunda-feira' THEN 1"
				+ " WHEN semana='Terça-feira' THEN 2 "
				+ " WHEN semana='Quarta-feira' THEN 3"
				+ " WHEN semana='Quinta-feira' THEN 4 "
				+ " WHEN semana='Sexta-feira' THEN 5 "
				+ " WHEN semana='Sábado' THEN 6"
				+ " WHEN semana='Domingo' THEN 7 END";
	}
	
	
}
