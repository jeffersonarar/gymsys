package br.ueg.unucet.gymsys.Model;

import br.ueg.unucet.gymsys.Anotations.Campo;
import br.ueg.unucet.gymsys.Anotations.Table;
import br.ueg.unucet.gymsys.Model.Model;

@Table(nome="exercicio")
public class Exercicio extends Model<Integer>{
	
	public Exercicio() { }
	public Exercicio(	int idexercicio, String nome, Categoria idcategoria, 
						String responsavel,	String descricao, boolean ativo) { 
		this.idexercicio = idexercicio;
		this.idcategoria = idcategoria;
		this.nome= nome;
		this.responsavel = responsavel;
		this.descricao = descricao;
		this.ativo = ativo;
	}
	
	@Campo(nome="idexercicio", pk=true)
	private int idexercicio;

	@Campo(nome="nome",obrigatorio=true)
	private String nome;
	
	@Campo(nome="idcategoria",obrigatorio=true)
	private Categoria idcategoria;

	@Campo(nome="responsavel",obrigatorio=true)
	private String responsavel;
	
	@Campo(nome="descricao",obrigatorio=true)
	private String descricao;
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Campo(nome="ativo",obrigatorio=true)
	private boolean ativo;

	@Override
	public boolean getAtivo() {
		return ativo;
	}
	
	public int getIdexercicio() {
		return idexercicio;
	}

	public void setIdexercicio(int idexercicio) {
		this.idexercicio = idexercicio;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}
	
	public Categoria getIdcategoria() {
		return idcategoria;
	}

	public void setIdcategoria(Categoria idcategoria) {
		this.idcategoria = idcategoria;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	@Override
	public String getVariaveisPesquisarNome() {
		return "nome";
	}
	public String getCriterio() {
		return "idcategoria";
	}
	public String getOrdenacao() {
		// TODO Auto-generated method stub
		return "nome";
	}
	
}

