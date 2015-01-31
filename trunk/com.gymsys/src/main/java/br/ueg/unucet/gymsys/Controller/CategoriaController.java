package br.ueg.unucet.gymsys.Controller;

import java.sql.SQLException;
import java.util.List;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.CookieParam;

import br.ueg.unucet.gymsys.Colecao.ColecaoCategoria;
import br.ueg.unucet.gymsys.Enum.TypeMessage;
import br.ueg.unucet.gymsys.Model.Categoria;
import br.ueg.unucet.gymsys.Model.IModel;
import br.ueg.unucet.gymsys.Model.Model;
import br.ueg.unucet.gymsys.Util.Response;

public class CategoriaController extends GenericController<Categoria> {
	
	private Categoria categoria = new Categoria();
	private Categoria categoriaSeleciona;
	
	public void setCategoriaSelecionada(Categoria categoria) {
		this.categoriaSeleciona = categoria;
	}
	
	@Override
	public List<?> getLstEntities(String keyword) {
		ColecaoCategoria listaCategoria = new ColecaoCategoria();
		try {
			if (categoriaSeleciona == null) {
				listaCategoria.setAll(dao.pesquisarNome(categoria, keyword));
			}
			else {
				//listaCategoria.setAll(dao.pesquisarNome(categoriaSeleciona, keyword));
				listaCategoria.setAll(dao.pesquisarPorCategoriaOuNome(categoria, keyword, categoriaSeleciona.getIdcategoria()));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaCategoria.getAll();
	}
	
	public List<?> getLstCriteria(String keyword) {
		ColecaoCategoria listaCategoria = new ColecaoCategoria();
		try {
			listaCategoria.setAll(dao.pesquisarCriterio(categoria, Integer.parseInt(keyword)));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaCategoria.getAll();
	}
	
	public Categoria getCategoria() {
		return this.categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public Categoria getEntity(String id) {
		Categoria categoria = new Categoria();
		categoria.setIdcategoria(Integer.parseInt(id));
		ColecaoCategoria listaCategoria = new ColecaoCategoria();
		try {
			listaCategoria.setAll(dao.pesquisarID(categoria));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		categoria = ((ColecaoCategoria) listaCategoria).getIndice(0);
		return categoria;
	}


	@Override
	public Response validar(IModel<?> imodel) {
		setCategoria((br.ueg.unucet.gymsys.Model.Categoria) imodel);
		Response res = new Response(true,"", null);
		if(getCategoria().getNome() == null){
			return  new Response(false, "Campo nome é obrigatório!", TypeMessage.AVISO);
		}
		if(getCategoria().getDescricao()== null){
			return  new Response(false, "Campo Responsavel é obrigatório!", TypeMessage.AVISO);
		}
		
		return res;
	}	
	
	@Override
	public Response validarItemUnico(IModel<?> imodel) {
		Response res = new Response(true,"", null);
		return res;
	}
}