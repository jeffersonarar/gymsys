package br.ueg.unucet.gymsys.View;

import java.util.List;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;

import br.ueg.unucet.gymsys.Controller.CategoriaController;
import br.ueg.unucet.gymsys.Model.Categoria;
import br.ueg.unucet.gymsys.Model.IModel;
import br.ueg.unucet.gymsys.Util.Response;


public class CategoriaViewModel extends ViewModel<Categoria, CategoriaController> {
	

	private Categoria categoriaselecionada;
	private List<?> listCategoria;
	private List<?> categoriaList;
    private Categoria selectedCategoria;
    
	
	@Init
	public void Init(){	
		Session session;
		session = Executions.getCurrent().getSession();
		if(!verificarLoginIsFuncionario(session)){
			Executions.sendRedirect("/index.zul?msn=2");
		}
		super.init();
		String idcategoria;
		idcategoria = Executions.getCurrent().getParameter("idcategoria");
		if(idcategoria!= null){
			System.out.println("idcategoria = "+idcategoria);
			setEntity(controller.getEntity(idcategoria));
		}
	}


	@Command
	@NotifyChange("listCategoria")
	public Response listar() {
		if(keyword == null){
    		keyword = "";
    	}
		
		controller.setCategoriaSelecionada(categoriaselecionada);
		
		setListCategoria(controller.getLstEntities((keyword)));
		
    	return null;
	}
	
	
	@NotifyChange("categoriaList")
	public List<Categoria> getCategoriaList(){
		CategoriaController categoriaController = new CategoriaController();
		categoriaList = categoriaController.getLstEntities("");
    	return (List<Categoria>) categoriaList;
    }
	
	@Override
	@Command
	public Response salvar(IModel<?> imodel) {
		if((Integer)getEntity().getPK() == 0){
			getEntity().setAtivo(true);
		}
		if(getCategoriaselecionada() == null){
			super.salvar(getEntity());
		}
		getEntity().setSupcategoria(getCategoriaselecionada());
	    return super.salvar(getEntity());
	}

	
	public Response desativar(IModel<?> imodel) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Categoria getCategoriaselecionada() {
		return categoriaselecionada;
	}

	public void setCategoriaselecionada(Categoria categoriaselecionada) {
		this.categoriaselecionada = categoriaselecionada;
	}
	
	public List<?> getListCategoria() {
		return listCategoria;
	}

	public void setListCategoria(List<?> listCategoria) {
		this.listCategoria = listCategoria;
	}

	public Categoria getSelectedCategoria() {
		return selectedCategoria;
	}

	public void setSelectedCategoria(Categoria selectedCategoria) {
		this.selectedCategoria = selectedCategoria;
	}
	

	@Override
	public Categoria getObject() {
		return new Categoria();
	}

	@Override
	public CategoriaController getControl() {
		return new CategoriaController();
	}
	
	@Command
	public void fechar(){
		Executions.sendRedirect("/categoria/listarCategoria.zul");
	}

}
