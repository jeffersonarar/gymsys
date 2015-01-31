package br.ueg.unucet.gymsys.Controller;

import java.sql.SQLException;
import java.util.List;

import br.ueg.unucet.gymsys.Colecao.ColecaoMultimidia;
import br.ueg.unucet.gymsys.Model.IModel;
import br.ueg.unucet.gymsys.Model.Multimidia;
import br.ueg.unucet.gymsys.Util.Response;
import br.ueg.unucet.gymsys.Util.Utils;
import br.ueg.unucet.gymsys.DAO.MultimidiaDAO;

public class MultimidiaController extends GenericController<Multimidia> {
	
	private Multimidia multimidia = new Multimidia();
	
	@Override
	public List<?> getLstEntities(String keyword) {
		Multimidia multimidia = new Multimidia();
		ColecaoMultimidia clMultimidia = new ColecaoMultimidia();
		try {
			clMultimidia.setAll(dao.pesquisarNome(multimidia, keyword));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return clMultimidia.getAll();
	}
	
	@Override
	public List<?> getLstEntitiesAtivos(String keyword) {
		Multimidia multimidia = new Multimidia();
		ColecaoMultimidia clMultimidia = new ColecaoMultimidia();
		try {
			clMultimidia.setAll(dao.pesquisarNomeAtivo(multimidia, keyword));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return clMultimidia.getAll();
	}
	
	
	public Multimidia getEntity(String id) {
		Multimidia multimidia = new Multimidia();
		multimidia.setIdmultimidia(Integer.parseInt(id));
		ColecaoMultimidia clMultimidia = new ColecaoMultimidia();
		try {
			clMultimidia.setAll(dao.pesquisarID(multimidia));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		multimidia = ((ColecaoMultimidia) clMultimidia).getIndice(0);
		return multimidia;
	}

	public Multimidia getMultimidia() {
		return multimidia;
	}

	public void setMultimidia(Multimidia multimidia) {
		this.multimidia = multimidia;
	}

	@Override
	public Response validar(IModel<?> imodel) {
		Response res = new Response(true,"", null);
		return res;
	}	
	
	@Override
	public Response validarItemUnico(IModel<?> imodel) {
		Response res = new Response(true,"", null);
		return res;
	}

	public String getnomeAUX() {
		MultimidiaDAO multimidiaDAO = new MultimidiaDAO();
		try {
			return multimidiaDAO.pesquisarProximoID();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public boolean validarMultimidia(String name, boolean tipomultimidia) {
		String estensao = Utils.retirarExtensao(name);
		if (!tipomultimidia){ //validar video...
			if(estensao.equalsIgnoreCase(".webm")){
				return true;
			}
		}else{ // validar imagem...
			if(estensao.equalsIgnoreCase(".jpg")||estensao.equalsIgnoreCase(".png")){
				return true;
			}
		}
		return false;
	}

	public Multimidia buscarImagemExercicio(int idexercicio) {
		Multimidia multimidia = new Multimidia();
		ColecaoMultimidia clMultimidia = new ColecaoMultimidia();
		try {
			MultimidiaDAO multimidiaDAO = new MultimidiaDAO();
			clMultimidia.setAll(multimidiaDAO.pesquisarImagemPorExercicio(multimidia, idexercicio));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		multimidia = ((ColecaoMultimidia) clMultimidia).getIndice(0);
		if( multimidia != null) {
			return multimidia;
		}
		return null;
	}

	public Multimidia buscarVideoExercicio(int idexercicio) throws SQLException {
		Multimidia multimidia = new Multimidia();
		ColecaoMultimidia clMultimidia = new ColecaoMultimidia();
		MultimidiaDAO multimidiaDAO = new MultimidiaDAO();
		clMultimidia.setAll(multimidiaDAO.pesquisarVideoPorExercicio(multimidia, idexercicio));
		multimidia = ((ColecaoMultimidia) clMultimidia).getIndice(0);
		if( multimidia != null) {
			return multimidia;
		}
		return null;
	}
}
