package br.ueg.unucet.gymsys.View;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.zkoss.bind.BindContext;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.image.AImage;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Image;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;








import br.ueg.unucet.gymsys.Controller.AlunoController;
import br.ueg.unucet.gymsys.Controller.CategoriaController;
import br.ueg.unucet.gymsys.Controller.ExercicioController;
import br.ueg.unucet.gymsys.Controller.MultimidiaController;
import br.ueg.unucet.gymsys.Enum.TypeMessage;
import br.ueg.unucet.gymsys.Model.Aluno;
import br.ueg.unucet.gymsys.Model.Categoria;
import br.ueg.unucet.gymsys.Model.Exercicio;
import br.ueg.unucet.gymsys.Model.IModel;
import br.ueg.unucet.gymsys.Model.Multimidia;
import br.ueg.unucet.gymsys.Util.Response;
import br.ueg.unucet.gymsys.Util.Utils;

@SuppressWarnings("serial")
public class MultimidiaViewModel<C> extends
		ViewModel<Multimidia, MultimidiaController> {

	@Wire("#imagens")
	private org.zkoss.zul.Image imagens;
	
	@Wire("#imagem")
	private Window imagem;
	
	@Wire("#fldnome")
	private Textbox fldnome;
	
	private Exercicio exercicioselecionado;
	private List<?> exercicioList;
	private List<?> multimidiaList;
	private Multimidia selectedMultimidia;
	private Categoria categoriaselecionada;
	private List<?> categoriaList;
	private Aluno alunoselecionada;
	private List<?> alunoList;
	private Media media;
	private String caminhoVideo = "video/ski.webm";
	private String nomeEntidade = "Exercicio";
	private String caminhoImagem = "imagem/erro.jpg";
	private String detalhe = " ";
	
	@Init
	public void init() {
		Session session;
		session = Executions.getCurrent().getSession();
		if(!verificarLoginIsFuncionario(session)){
			Executions.sendRedirect("/index.zul?msn=2");
		}
		super.init();
		String idmultimidia;
		idmultimidia = Executions.getCurrent().getParameter("idmultimidia");
		if (idmultimidia != null) {
			System.out.println("idmultimidia = " + idmultimidia);
			setEntity(controller.getEntity(idmultimidia));
		}
		iniciarParametrosSessao();
	}

	@Override
	public Multimidia getObject() {
		return new Multimidia();
	}

	@Override
	public MultimidiaController getControl() {
		return new MultimidiaController();
	}

	@Command("upload")
	public void upload(BindContext ctx, @ContextParam(ContextType.VIEW) Component view) {
			 Selectors.wireEventListeners(view, this);
		UploadEvent event = (UploadEvent) ctx.getTriggerEvent();
		setMedia(event.getMedia());
		media.getName();
		 setFldnome((Textbox) view.getFellow("fldnome"));
		try {
			fldnome.setValue(getMedia().getName());
			getEntity().setNome(getMedia().getName());
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}


	

	@Command
	@NotifyChange("multimidiaList")
	public Response listar() {
		if (keyword == null) {
			keyword = "";
		}
		System.out.println("Tem que devolver a lista de acordo com a keyword...");
		setMultimidiaList(controller.getLstEntities((keyword)));
		return null;
	}

	public Multimidia getSelectedMultimidia() {
		return selectedMultimidia;
	}

	public void setSelectedMultimidia(Multimidia selectedMultimidia) {
		this.selectedMultimidia = selectedMultimidia;
	}

	public List<?> getMultimidiaList() {
		return multimidiaList;
	}

	public void setMultimidiaList(List<?> multimidiaList) {
		this.multimidiaList = multimidiaList;
	}

	@Command    
	public void telaAlterar() {
	
		if(getSelectedMultimidia()!=null){
			if(getSelectedMultimidia().getExercicio() != null){
				Executions.sendRedirect("/multimidia/frmMultimidia.zul?idmultimidia="
						+ getSelectedMultimidia().getIdmultimidia());
			} else if(getSelectedMultimidia().getCategoria() != null){
				Executions.sendRedirect("/multimidia/frmMultimidia1.zul?idmultimidia="
						+ getSelectedMultimidia().getIdmultimidia());
			} else if(getSelectedMultimidia().getAluno() != null){
				Executions.sendRedirect("/multimidia/frmMultimidia2.zul?idmultimidia="
						+ getSelectedMultimidia().getIdmultimidia());
			}	
		}else{
			Response res = new Response(false, "Não foi selecionado nenhum item!", TypeMessage.ERROR);
			msgbox.mensagem(res.getTypeMessage(), res.getMensagem());
		}
		
		

	}

	
	@SuppressWarnings("unchecked")
	@NotifyChange("exercicioList")
	public List<Exercicio> getExercicioList() {
		ExercicioController exercicioController = new ExercicioController();
		exercicioList = exercicioController.getLstEntities("");
		return (List<Exercicio>) exercicioList;
	}
	


	@SuppressWarnings("unchecked")
	@NotifyChange("categoriaList")
	public List<Categoria> getCategoriaList(){
		CategoriaController categoriaController = new CategoriaController();
		categoriaList = categoriaController.getLstEntities("");
    	return (List<Categoria>) categoriaList;
    }
	
	@SuppressWarnings("unchecked")
	@NotifyChange("alunoList")
	public List<Aluno> getAlunoList(){
		AlunoController alunoController = new AlunoController();
	    alunoList = alunoController.getLstEntities("");
    	return (List<Aluno>) alunoList;
    }

	
	@Command
	public void visualizar(@BindingParam("idmultimidia") String idmultimidia, @ContextParam(ContextType.VIEW) Component view) {
		String teste = idmultimidia;
		setSelectedMultimidia(controller.getEntity(teste)); 
		if(!getSelectedMultimidia().getTipomultimidia()){ //Verdadeiro quando tipo de Multimidia é uma imagem...
			Window window = (Window)Executions.createComponents(
	                "/multimidia/visualizarImagem.zul", null, null);
			Selectors.wireEventListeners(window, this);
			setKeyword(getSelectedMultimidia().getNome());
			 File arquivo = new File("C:/Users/Notebook/workspace/com.gymsys/src/webapp/multimidia/"+ getSelectedMultimidia().getMultimidia() );
			 setImagem((Window) window.getFellow("imagem"));
			 setImagens((Image) window.getFellow("imagem").getFellow("imagens"));
			 try {
				imagens.setContent(new AImage(arquivo));
			} catch (IOException e) {
				 //TODO Auto-generated catch block
				e.printStackTrace();
			} 
			imagens.setParent(imagem);
			imagem.setClosable(true);
	        window.doModal();
		}else{
			String caminhoarquivo = (getSelectedMultimidia().getMultimidia());
			Session session;
			session = Executions.getCurrent().getSession();
			session.setAttribute("caminho", caminhoarquivo);
			if(getSelectedMultimidia().getCategoria() != null){
				session.setAttribute("nomeEntidade", getSelectedMultimidia().getCategoria().getNome());
			}
			if(getSelectedMultimidia().getExercicio() != null){
				session.setAttribute("nomeEntidade", getSelectedMultimidia().getExercicio().getNome());
			}
			if(getSelectedMultimidia().getAluno() != null){
				session.setAttribute("nomeEntidade", getSelectedMultimidia().getAluno().getNome());
			}
			Window window = (Window)Executions.createComponents(
	                "/multimidia/visualizarVideo2.zul", null, null);
			//setCaminhoVideo(arquivo.getPath());
			setCaminhoVideo(caminhoarquivo);
			Selectors.wireEventListeners(window, this);
	        window.doModal();
		}
    }

	public Exercicio getExercicioselecionado() {
		return exercicioselecionado;
	}

	public void setExercicioselecionado(Exercicio exercicioselecionado) {
		this.exercicioselecionado = exercicioselecionado;
	}

	public Textbox getFldnome() {
		return fldnome;
	}

	public void setFldnome(Textbox fldnome) {
		this.fldnome = fldnome;
	}

	public void setExercicioList(List<?> exercicioList) {
		this.exercicioList = exercicioList;
	}

	@Command
	public Response salvar(IModel<?> imodel) {
		if(verificarFormato()){
		if(getMedia() != null){
			if((Integer)getEntity().getPK() == 0){
				getEntity().setAtivo(true);
			}
			String nomeAUX = getControl().getnomeAUX();
			if(nomeAUX != null){
				String local = null;
				String extensao = Utils.retirarExtensao((String) (getMedia().getName()));
				if(!getEntity().getTipomultimidia()){
					local = "C:/Users/Notebook/workspace/com.gymsys/src/webapp/multimidia/video/" + nomeAUX + extensao;
				}else{
					local = "C:/Users/Notebook/workspace/com.gymsys/src/webapp/multimidia/imagem/"  + nomeAUX + extensao;
				}
				InputStream in =  getMedia().getStreamData();
				File arquivo = new File(local);
				OutputStream out = null;
				try {
					out = new FileOutputStream(local);
				} catch (FileNotFoundException e) {	
					e.printStackTrace();
				}
				try { 	
					byte[] buffer = new byte[1024];  
			          
					while(in.read(buffer)>0){  
					   out.write(buffer);  
					}  
					          
					out.close();  
					in.close(); 
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				if(!getEntity().getTipomultimidia()){
					local = "video/" + nomeAUX + extensao;
				}else{
					local = "imagem/" + nomeAUX + extensao;
				}
				getEntity().setMultimidia(local); 
				getEntity().setNome(getMedia().getName());
				if (getExercicioselecionado() != null) {
					getEntity().setExercicio(getExercicioselecionado());
					getEntity().setDatainsercao(new Date());
					super.salvar(getEntity());
				}
				if (getCategoriaselecionada() != null) {
					getEntity().setCategoria(getCategoriaselecionada());
					getEntity().setDatainsercao(new Date());
					super.salvar(getEntity());
				}
				if (getAlunoselecionada() != null) {
					getEntity().setAluno(getAlunoselecionada());
					getEntity().setDatainsercao(new Date());
					super.salvar(getEntity());
				}
			}else{
				msgbox.mensagemSucesso("Erro ao salvar a multimidia escolhida...");
			}
		}
		}
		
		
		return null;

	}


	private boolean verificarFormato() {
		if(!getControl().validarMultimidia(getMedia().getName(), getEntity().getTipomultimidia())){
			if(!getEntity().getTipomultimidia()){ //video...
				msgbox.mensagemError("Formato de video não suportado,  escolher vídeo com formato webm");
			}else{ // imagem...
				msgbox.mensagemError("Formato de imagem não suportado, escolher imagem com formato jpg ou png");
			}
			return false;
		}
		return true;
	}

	public void setCategoriaList(List<?> categoriaList) {
		this.categoriaList = categoriaList;
	}
	
	
	public Categoria getCategoriaselecionada() {
		return categoriaselecionada;
	}

	public void setCategoriaselecionada(Categoria categoriaselecionada) {
		this.categoriaselecionada = categoriaselecionada;
	}

	public Aluno getAlunoselecionada() {
		return alunoselecionada;
	}

	public void setAlunoselecionada(Aluno alunoselecionada) {
		this.alunoselecionada = alunoselecionada;
	}


	public void setAlunoList(List<?> alunoList) {
		this.alunoList = alunoList;
	}

	public org.zkoss.zul.Image getImagens() {
		return imagens;
	}

	public void setImagens(org.zkoss.zul.Image imagens) {
		this.imagens = imagens;
	}

	public Window getImagem() {
		return imagem;
	}

	public void setImagem(Window imagem) {
		this.imagem = imagem;
	}
	
	@Command
	public void fechar(){
		Executions.sendRedirect("/multimidia/listarMultimidia.zul");
	}

	public Media getMedia() {
		return media;
	}

	public void setMedia(Media media) {
		this.media = media;
	}

	public Response desativar(IModel<?> imodel) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getCaminhoVideo() {
		Session session;
		session = Executions.getCurrent().getSession();
		if(session.getAttribute("caminho") == null){
			
		}else{
			caminhoVideo = (String) session.getAttribute("caminho");
			session.removeAttribute("caminho");
		}
		return caminhoVideo;
	}

	public void setCaminhoVideo(String caminhoVideo) {
		this.caminhoVideo = caminhoVideo;
	}

	public String getNomeEntidade() {
		Session session;
		session = Executions.getCurrent().getSession();
		if(session.getAttribute("nomeEntidade") == null){
			return "Erro ao abrir este vídeo...";
		}else{
			nomeEntidade = (String) session.getAttribute("nomeEntidade");
			session.removeAttribute("nomeEntidade");
		}
		return nomeEntidade;
	}

	public void setNomeEntidade(String nomeEntidade) {
		this.nomeEntidade = nomeEntidade;
	}

	private void iniciarParametrosSessao() {
		Session session;
		session = Executions.getCurrent().getSession();
		String camImagem = (String) session.getAttribute("caminhoImagem");
		session.removeAttribute("caminhoImagem");
		String camVideo =  (String) session.getAttribute("caminhoVideo");
		session.removeAttribute("caminhoVideo");
		String detalhe =  (String) session.getAttribute("detalhe");
		session.removeAttribute("detalhe");
		
		setCaminhoImagem(camImagem);
		setCaminhoVideo(camVideo);
		setDetalhe(detalhe);
	}

	public String getCaminhoImagem() {
		return caminhoImagem;
	}

	public void setCaminhoImagem(String caminhoImagem) {
		this.caminhoImagem = caminhoImagem;
	}

	public String getDetalhe() {
		return detalhe;
	}

	public void setDetalhe(String detalhe) {
		this.detalhe = detalhe;
	}
	
}
