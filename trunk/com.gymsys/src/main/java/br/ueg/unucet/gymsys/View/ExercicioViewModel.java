package br.ueg.unucet.gymsys.View;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.bind.BindContext;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zul.Window;

import br.ueg.unucet.gymsys.Controller.CategoriaController;
import br.ueg.unucet.gymsys.Controller.ExercicioController;
import br.ueg.unucet.gymsys.Model.Categoria;
import br.ueg.unucet.gymsys.Model.IModel;
import br.ueg.unucet.gymsys.Model.Exercicio;
import br.ueg.unucet.gymsys.Util.Response;

@SuppressWarnings("serial")
public class ExercicioViewModel extends ViewModel<Exercicio, ExercicioController> {
	
	private Categoria categoriaselecionada;
	private List<?> exercicioList;
	private List<?> categoriaList;
    private Exercicio selectedEntity;
	


	@Init
	public void Init(){	
		Session session;
		session = Executions.getCurrent().getSession();
		if(!verificarLoginIsFuncionario(session)){
			Executions.sendRedirect("/index.zul?msn=2");
		}
		super.init();
		String id;
		id = Executions.getCurrent().getParameter("idexercicio");
		if(id!= null){
			System.out.println("id = "+id);
			setEntity(controller.getEntity(id));
			setCategoriaselecionada(getEntity().getIdcategoria());
		}
	}
	
	@Override
	public ExercicioController getControl() {
		return new ExercicioController();
	}
	@Override
	public Exercicio getObject() {
		return new Exercicio();
	}
	
	
    @Command
    @NotifyChange("exercicioList")
    public Response listar() {
    	if(keyword == null){
    		keyword = "";
    	}
    	if(displayEdit){
    		exercicioList = controller.getLstEntitiesAtivos((keyword));
    	}else{
    		exercicioList = controller.getLstEntities((keyword));
    	}
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
		getEntity().setIdcategoria(getCategoriaselecionada());
	    return super.salvar(getEntity());
	}
	   
    @Command
    public void closeWindows() {
    	Executions.sendRedirect("/index.zul");
    }
    
	public Response desativar(IModel<?> imodel) {
		return null;
	}
	
	public void setSelectedEntity(Exercicio selectedEntity) {
        this.selectedEntity = selectedEntity;
    }
	
    public Exercicio getSelectedEntity() {
        return selectedEntity;
    }
    
	public List<?> getExercicioList() {
		return exercicioList;
	}
	
	 
    public Categoria getCategoriaselecionada() {
		return categoriaselecionada;
	}
	public void setCategoriaselecionada(Categoria categoriaselecionada) {
		this.categoriaselecionada = categoriaselecionada;
	}

}
