package br.ueg.unucet.gymsys.Controller;

import java.sql.SQLException;
import java.util.List;

import br.ueg.unucet.gymsys.Colecao.ColecaoExercicio;
import br.ueg.unucet.gymsys.Colecao.ColecaoFichaTreino;
import br.ueg.unucet.gymsys.Colecao.ColecaoMultimidia;
import br.ueg.unucet.gymsys.Enum.TypeMessage;
import br.ueg.unucet.gymsys.Model.Exercicio;
import br.ueg.unucet.gymsys.Model.FichaTreino;
import br.ueg.unucet.gymsys.Model.IModel;
import br.ueg.unucet.gymsys.Model.Multimidia;
import br.ueg.unucet.gymsys.Util.Response;

public class FichaTreinoController extends GenericController<FichaTreino> {
	private FichaTreino fichaTreino = new FichaTreino();

	public List<?> getLstEntities(String keyword) {
		FichaTreino ficha = new FichaTreino();
		ColecaoFichaTreino clFichaTreino = new ColecaoFichaTreino();
		try {
			clFichaTreino.setAll(dao.pesquisarCriterio(ficha, Integer.parseInt(keyword)));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return clFichaTreino.getAll();
	}
	
	public FichaTreino getEntity(String id) {
		FichaTreino ficha = new FichaTreino();
		
		ficha.setIdfichatreino(Integer.parseInt(id));
		ColecaoFichaTreino clFichaTreino = new ColecaoFichaTreino();
		try {
			clFichaTreino.setAll(dao.pesquisarID(ficha));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ficha = clFichaTreino.getIndice(0);
		return ficha;
	}
	
	public Response validar(IModel<?> imodel) {
		setFichaTreino((br.ueg.unucet.gymsys.Model.FichaTreino) imodel);
		Response res = new Response(true,"", null);
		
		if(getFichaTreino().getExercicio() == null){
			return  new Response(false, "Campo exercicio é obrigatório!", TypeMessage.AVISO);
		}
		if(getFichaTreino().getCategoria() == null){
			return  new Response(false, "Campo categoria é obrigatório!", TypeMessage.AVISO);
		}
		if(getFichaTreino().getAluno() == null){
			return  new Response(false, "Campo categoria é obrigatório!", TypeMessage.AVISO);
		}
		if(getFichaTreino().getSemana() == null){
			return  new Response(false, "Campo semana é obrigatório!", TypeMessage.AVISO);
		}
		if(getFichaTreino().getSerie() == null){
			return  new Response(false, "Campo serie é obrigatório!", TypeMessage.AVISO);
		}
		if(getFichaTreino().getRepeticao() == null){
			return  new Response(false, "Campo repetição é obrigatório!", TypeMessage.AVISO);
		}
		return res;
	}
	
	public FichaTreino getFichaTreino() {
		return fichaTreino;
	}
	public void setFichaTreino(FichaTreino fichaTreino) {
		this.fichaTreino = fichaTreino;
	}


	@Override
	public Response validarItemUnico(IModel<?> imodel) {
		Response res = new Response(true,"", null);
		return res;
	}

}
