package br.ueg.unucet.gymsys.Enum;

public enum Serie {

	UM(1), 
	DOIS(2), 
	TRES(3),
	QUATRO(4),
	CINCO(5), 
	SEIS(6), 
	SETE(7),
	OITO(8),
	NOVE(9), 
	DEZ(10),;
	
	private Integer id;

	private Serie(Integer id) {
		this.setId(id);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
