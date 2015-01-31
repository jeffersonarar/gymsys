package br.ueg.unucet.gymsys.Enum;

public enum Semana {

	
	DOMINGO(1, "Domingo"), 
	SEGUNDA_FEIRA(2, "Segunda-feira"), 
	TER�A_FEIRA(3,"Ter�a-feira"),
	QUARTA_FEIRA(4,"Quarta-feira"),
	QUINTA_FEIRA(5,"Quinta-feira"),
	SEXTA_FEIRA(6,"Sexta-feira"),
	SABADO(7,"S�bado"),;
	
	private Integer id;
	private String descricao;

	private Semana(Integer id, String descricao) {
		this.setId(id);
		this.setDescricao(descricao);
	}
	
	

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
