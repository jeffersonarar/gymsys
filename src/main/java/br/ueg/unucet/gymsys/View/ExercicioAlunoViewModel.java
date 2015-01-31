package br.ueg.unucet.gymsys.View;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.zkoss.bind.BindContext;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.image.AImage;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.Popup;
import org.zkoss.zul.Window;

import br.ueg.unucet.gymsys.Controller.CategoriaController;
import br.ueg.unucet.gymsys.Controller.ExercicioController;
import br.ueg.unucet.gymsys.Controller.MultimidiaController;
import br.ueg.unucet.gymsys.Controller.OrdenadorExercicioController;
import br.ueg.unucet.gymsys.Model.Categoria;
import br.ueg.unucet.gymsys.Model.IModel;
import br.ueg.unucet.gymsys.Model.Exercicio;
import br.ueg.unucet.gymsys.Model.OrdenadorExercicio;
import br.ueg.unucet.gymsys.Util.Response;

@SuppressWarnings("serial")
public class ExercicioAlunoViewModel extends ViewModel<OrdenadorExercicio, OrdenadorExercicioController> {
	
	private Categoria categoriaselecionada;
	private List<?> exercicioList;
	private List<?> categoriaList;
    private OrdenadorExercicio selectedEntity;
	private String nomeImagem;
	private String caminhoVideo = "video/ski.webm";
	
	 @Wire("#msgPopup")
	 Popup popup;
	 @Wire("#image")
	 Image image;
	 @Wire("#msg2")
	 Label msg;
	 
	 @Wire("#imagemExercicio")
	 private Image imagemExercicio;

	 @AfterCompose
	    public void afterCompose(@ContextParam(ContextType.VIEW) Component view){
	        Selectors.wireComponents(view, this, false);
	        //wire event listener
//	      Selectors.wireEventListeners(view, this);
	    }
	  //...
	 @Command
	 public void popupMessage(@BindingParam("target")Component targetComponent, @BindingParam("content")String content, @BindingParam("content2")String content2){
		 if(content2 != null && !content2.equalsIgnoreCase("")){
			 image.setSrc("../../com.gymsys/multimidia/"+content2);
		 }else{
			 image.setSrc("../../com.gymsys/img/erro.jpg");  
		 }
		 msg.setValue(content);
		 popup.open(targetComponent,"end_before");
	 }

	@Init
	public void Init(){		
		super.init();
		String id;
		id = Executions.getCurrent().getParameter("idexercicio");
		if(id!= null){
			System.out.println("id = "+id);
			setEntity(controller.getEntity(id));
		}
	}
	
	@Override
	public OrdenadorExercicioController getControl() {
		return new OrdenadorExercicioController();
	}
	@Override
	public OrdenadorExercicio getObject() {
		return new OrdenadorExercicio();
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
	    return super.salvar(getEntity());
	}
	   
    @Command
    public void closeWindows() {
    	Executions.sendRedirect("/index.zul");
    }
    
	public Response desativar(IModel<?> imodel) {
		return null;
	}
	
	public void setSelectedEntity(OrdenadorExercicio selectedEntity) {
        this.selectedEntity = selectedEntity;
    }
	
    public OrdenadorExercicio getSelectedEntity() {
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
	
	 public String getNomeImagem() {
		 if(nomeImagem == null ){
			 nomeImagem = "../../com.gymsys/img/erro.jpg";
		 }
			return nomeImagem;
		}

		public void setNomeImagem(String nomeImagem) {
			this.nomeImagem = nomeImagem;
		}
		public Popup getPopup() {
			return popup;
		}
		public void setPopup(Popup popup) {
			this.popup = popup;
		}
		public Image getImage() {
			return image;
		}
		public void setImage(Image image) {
			this.image = image;
		}
		public Label getMsg() {
			return msg;
		}
		public void setMsg(Label msg) {
			this.msg = msg;
		}

		@Command
	    @NotifyChange("exercicioList")
		public void ListCategoria() {
			exercicioList = controller.getListarExercicioPorCategoria((getCategoriaselecionada().getIdcategoria()));
		}
		
		@Command
		public void listarDetalheExercicio(@ContextParam(ContextType.VIEW) Component view) {
			if(getSelectedEntity() != null){
					Session session;
					session = Executions.getCurrent().getSession();
					session.setAttribute("nomeEntidade", getSelectedEntity().getExercicio().getNome());
					session.setAttribute("detalhe", getSelectedEntity().getExercicio().getDescricao());
					if(getSelectedEntity().getImagem()!= null){
						String content2 = getSelectedEntity().getImagem().getMultimidia();
						if(content2 != null && !content2.equalsIgnoreCase("")){
							session.setAttribute("caminhoImagem", "../../com.gymsys/multimidia/"+content2);
						 }else{
							session.setAttribute("caminhoImagem", "../../com.gymsys/img/erro.jpg");
						 }
					}else{
						session.setAttribute("caminhoImagem", "../../com.gymsys/img/erro.jpg");
					}
					if(getSelectedEntity().getVideo() != null){
						String caminhovideo = (getSelectedEntity().getVideo().getMultimidia());
						if(caminhovideo != null && !caminhovideo.equalsIgnoreCase("")){
							session.setAttribute("caminhoVideo", "../../com.gymsys/multimidia/"+caminhovideo);
						 }else{
							session.setAttribute("caminhoVideo", "../../com.gymsys/multimidia/video/erro.webm");
						 }
					}else{
						session.setAttribute("caminhoVideo", "../../com.gymsys/multimidia/video/erro.webm");
					}
					Window window = (Window)Executions.createComponents(
			                "/multimidia/detalhesExercicio.zul", null, null);
					Selectors.wireEventListeners(window, this);
			        window.doModal();
				}	 
		}
		public String getCaminhoVideo() {
			return caminhoVideo;
		}
		public void setCaminhoVideo(String caminhoVideo) {
			this.caminhoVideo = caminhoVideo;
		}
		
}
