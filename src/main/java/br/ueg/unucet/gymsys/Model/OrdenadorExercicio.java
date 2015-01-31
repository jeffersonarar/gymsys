package br.ueg.unucet.gymsys.Model;

public class OrdenadorExercicio extends Model<Integer> {
	 private Exercicio exercicio;
	 private Multimidia video;
	 private Multimidia imagem;
	 public OrdenadorExercicio(){
		
	 }

	 
	 public OrdenadorExercicio(Exercicio exercicio, Multimidia video, Multimidia imagem){
		 this.exercicio = exercicio;
		 this.video = video;
		 this.imagem = imagem;
	 }

	public Exercicio getExercicio() {
		return exercicio;
	}

	public void setExercicio(Exercicio exercicio) {
		this.exercicio = exercicio;
	}

	public Multimidia getVideo() {
		return video;
	}

	public void setVideo(Multimidia video) {
		this.video = video;
	}

	public Multimidia getImagem() {
		return imagem;
	}

	public void setImagem(Multimidia imagem) {
		this.imagem = imagem;
	}


	@Override
	public String getOrdenacao() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String getVariaveisPesquisarNome() {
		// TODO Auto-generated method stub
		return null;
	}
	 

}
