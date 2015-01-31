package br.ueg.unucet.gymsys.Model;

import br.ueg.unucet.gymsys.Anotations.Campo;
import br.ueg.unucet.gymsys.Anotations.Table;

@Table(nome="categoria")
public class Categoria extends Model<Integer>{
	
	public Categoria() { }
	public Categoria(	int idcategoria, String nome, Categoria supcategoria, 
						String descricao, boolean ativo) { 
		this.idcategoria = idcategoria;
		this.supcategoria = supcategoria;
		this.nome= nome;
		this.descricao = descricao;
		this.ativo = ativo;
	}
	
	@Campo(nome="idcategoria", pk=true)
	private int idcategoria;

	@Campo(nome="nome",obrigatorio=true)
	private String nome;
	
	@Campo(nome="descricao",obrigatorio=true)
	private String descricao;
	
	@Campo(nome="supcategoria",obrigatorio=true)
	private Categoria supcategoria;

	@Campo(nome="ativo",obrigatorio=true)
	private boolean ativo;

	@Override
	public boolean getAtivo() {
		return ativo;
	}
	@Override
	public String getVariaveisPesquisarNome() {
		return "nome";
	}
	
	public int getIdcategoria() {
		return idcategoria;
	}
	public void setIdcategoria(int idcategoria) {
		this.idcategoria = idcategoria;
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
	public Categoria getSupcategoria() {
		return supcategoria;
	}
	public void setSupcategoria(Categoria supcategoria) {
		this.supcategoria = supcategoria;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public String getCriterio() {
		return "supcategoria";
	}
	public String getOrdenacao() {
		return "nome";
	}

}
