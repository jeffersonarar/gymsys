package br.ueg.unucet.gymsys.View;

import java.util.Date;
import java.util.List;

import net.sf.jasperreports.engine.JRException;

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
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zul.Window;

import br.ueg.unucet.gymsys.Controller.AlunoController;
import br.ueg.unucet.gymsys.Enum.TypeMessage;
import br.ueg.unucet.gymsys.Model.Aluno;
import br.ueg.unucet.gymsys.Model.IModel;
import br.ueg.unucet.gymsys.Util.Response;


public class AlunoViewModel extends ViewModel<Aluno, AlunoController> {

	private String keyword;
	private List<?> alunoList;
	private List<?> alunoHistoricoList;
	private Aluno selectedAluno;
	private Window windowAluno;

    @Init
	public void Init(){		
    	super.init();
		String idaluno;
		Session session;
		session = Executions.getCurrent().getSession();
		if(!verificarLoginIsAluno(session)){
			if(!verificarLoginIsFuncionario(session)){
				Executions.sendRedirect("/index.zul?msn=2");
			}
		}
		
		idaluno = Executions.getCurrent().getParameter("idaluno");
		if(idaluno!= null){
			System.out.println("idaluno = "+idaluno);
			setEntity(controller.getEntity(idaluno));
			//keyword = getEntity().getSenha();
		}
		String idUsuario = null;
		idUsuario = (String) session.getAttribute("aluno.idusuario");
		if(Executions.getCurrent().getSession().getAttribute("status")!=null){
			if(!Executions.getCurrent().getSession().getAttribute("status").equals("onlineFuncionario")){
				setEntity(getControl().pesquisarAlunoIdusuario(String.valueOf(Executions.getCurrent().getSession().getAttribute("id"))));
				setSelectedEntity(getEntity());
			
			}
	    }
		
		
		if(idUsuario != null){
				setEntity(getControl().pesquisarAlunoIdusuario(idUsuario));
		}
		String url = null;
		url = Executions.getCurrent().getParameter("action");
		if(url != null ){
			if(url.equalsIgnoreCase("1")){
				listar();
			}
		}
		
	}

	public AlunoController getControl() {
		return new AlunoController();
	}
	
	@Override
	@Command
	public Response salvar(IModel<?> imodel) {
		super.salvar(getEntity());
		return null;
	}
	
	
    
    @Command
    public void close(BindContext ctx,
			@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireEventListeners(view, this);
		setWindowAluno((Window) view.getFellow("windowAluno"));
	    getWindowAluno().setVisible(false);

    }
   
    @Command
    public void closeWindows() {
    	Executions.sendRedirect("/index.zul");
    }
 
    @Command
    @NotifyChange("alunoHistoricoList")
    public Response listarHistorico(BindContext ctx,
			@ContextParam(ContextType.VIEW) Component view) {

		if(Executions.getCurrent().getSession().getAttribute("status")!=null){
			if(!Executions.getCurrent().getSession().getAttribute("status").equals("onlineFuncionario")){
				setEntity(getControl().pesquisarAlunoIdusuario(String.valueOf(Executions.getCurrent().getSession().getAttribute("id"))));
				setSelectedEntity(getEntity());
				alunoHistoricoList = controller.getLstCriteria(String.valueOf(getSelectedEntity().getIdaluno()));
			    return null;
			}
	    }
		Selectors.wireEventListeners(view, this);
		setWindowAluno((Window) view.getFellow("windowAluno"));
	 	getWindowAluno().setVisible(true);
	 	getWindowAluno().doModal(); 
    	alunoHistoricoList = controller.getLstCriteria(String.valueOf(getSelectedEntity().getIdaluno()));
    	return null;
    }
   


	@Command
	public void gerarRelatorio(){
		
		try {
			if(getAlunoHistoricoList()!=null){
				super.gerarRelatorio("reportHistorico", getAlunoHistoricoList(), "historicoAluno");
				
			}else{
				Response res = new Response(false, "Não há nada na lista!", TypeMessage.ERROR);
				msgbox.mensagem(res.getTypeMessage(), res.getMensagem());
			}
			
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	public Response desativar(IModel<?> imodel) {
		return null;
	}
	@Override
	public Aluno getObject() {
		return new Aluno();
	}

    
	public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
    public String getKeyword() {
        return keyword;
    }

	public Window getWindowAluno() {
		return windowAluno;
	}

	public void setWindowAluno(Window windowAluno) {
		this.windowAluno = windowAluno;
	}

	
	@SuppressWarnings("unchecked")
	public List<Aluno> getAlunoList(){
        return (List<Aluno>) alunoList;
    }
	
    @SuppressWarnings("unchecked")
	public List<Aluno> getAlunoHistoricoList() {
		return (List<Aluno>) alunoHistoricoList;
	}
    
	public void setAlunoHistoricoList(List<?> alunoHistoricoList) {
		this.alunoHistoricoList = alunoHistoricoList;
	}

	public void setSelectedAluno(Aluno selectedAluno) {
        this.selectedAluno = selectedAluno;
    }
    public Aluno getSelectedAluno() {
        return selectedAluno;
    }

    @Command
    @NotifyChange("alunoList")
    public Response listar() {
    	if(keyword == null){
    		keyword = "";
    	}
    	if(displayEdit){
    		alunoList = controller.getLstEntitiesAtivos((keyword));
    	}else{
    		alunoList = controller.getLstEntities((keyword));
    	}
    	return null;
    }
        
    
}