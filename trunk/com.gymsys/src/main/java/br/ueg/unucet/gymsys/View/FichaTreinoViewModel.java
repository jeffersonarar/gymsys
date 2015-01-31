package br.ueg.unucet.gymsys.View;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.jasperreports.engine.JRException;

import org.zkoss.bind.BindContext;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Vbox;
import org.zkoss.zul.Window;

import br.ueg.unucet.gymsys.Controller.AlunoController;
import br.ueg.unucet.gymsys.Controller.CategoriaController;
import br.ueg.unucet.gymsys.Controller.ExercicioController;
import br.ueg.unucet.gymsys.Controller.FichaTreinoController;
import br.ueg.unucet.gymsys.Enum.Semana;
import br.ueg.unucet.gymsys.Enum.Serie;
import br.ueg.unucet.gymsys.Enum.TypeMessage;
import br.ueg.unucet.gymsys.Model.Aluno;
import br.ueg.unucet.gymsys.Model.Categoria;
import br.ueg.unucet.gymsys.Model.Exercicio;
import br.ueg.unucet.gymsys.Model.FichaTreino;
import br.ueg.unucet.gymsys.Model.IModel;
import br.ueg.unucet.gymsys.Util.Response;

public class FichaTreinoViewModel extends
		ViewModel<FichaTreino, FichaTreinoController> {

	private Semana semanaselecionada;
	private Serie serieselecionada;
	private List<Semana> semanaList = new ArrayList<Semana>();
	private List<Serie> serieList = new ArrayList<Serie>();
	private Exercicio exercicioselecionado;
	private List<?> exercicioList;
	private List<?> multimidiaList;
	private Categoria categoriaselecionada;
	private List<?> categoriaList;
	private Aluno alunoselecionada;
	private List<?> alunoList;
	private List<?> fichaList;
	private FichaTreino selectedFichaTreino;
	private List<?> subcategoriaList;
	private Categoria subcategoriaselecionada;
	private Window windowAluno;
	private Textbox nomeAluno;
	private Vbox vboxRelatorio;
	

	
	@Init
	public void Init() {
		super.init();
	}

	@Override
	public FichaTreino getObject() {
		return new FichaTreino();
	}

	@Override
	public FichaTreinoController getControl() {
		return new FichaTreinoController();
	}
	
	@Command
	@SuppressWarnings("unchecked")
	@NotifyChange("exercicioList")
	public List<Exercicio> getExercicioList() {
		ExercicioController exercicioController = new ExercicioController();
		if(getSubcategoriaselecionada() == null){
			exercicioList = exercicioController.getLstEntities(".");
			return (List<Exercicio>) exercicioList;
		}
		exercicioList = exercicioController.getLstCriteria(String.valueOf(getSubcategoriaselecionada().getIdcategoria()));
		return (List<Exercicio>) exercicioList;
	}

	@Command
	@SuppressWarnings("unchecked")
	@NotifyChange("subcategoriaList")
	public List<?> getSubcategoriaList() {
		CategoriaController categoriaController = new CategoriaController();
		if (getCategoriaselecionada() == null) {
			subcategoriaList = (List<?>) categoriaController
					.getLstEntities(" ");
			return (List<Categoria>) subcategoriaList;
		}
		subcategoriaList = (List<Categoria>) categoriaController
				.getLstCriteria(String.valueOf(getCategoriaselecionada()
						.getIdcategoria()));
		return (List<Categoria>) subcategoriaList;
	}
	
	@SuppressWarnings("unchecked")
	@NotifyChange("categoriaList")
	public List<Categoria> getCategoriaList() {
		CategoriaController categoriaController = new CategoriaController();
		categoriaList = categoriaController.getLstEntities("");
		return (List<Categoria>) categoriaList;
	}
	
	@SuppressWarnings("unchecked")
	@NotifyChange("alunoList")
	@Command
	public List<Aluno> getAlunoList() {
		AlunoController alunoController = new AlunoController();
		if (keyword == null) {
			keyword = "";
		}
		System.out
				.println("Tem que devolver a lista de acordo com a keyword...");
		setAlunoList(alunoController.getLstEntities((keyword)));
		return (List<Aluno>) alunoList;
		
	}

	   
    @Command
    public void close(BindContext ctx,
			@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireEventListeners(view, this);
		setWindowAluno((Window) view.getFellow("windowAluno"));
	    getWindowAluno().setVisible(false);

    }
	
	@NotifyChange("semanaList")
	public List<Semana> getSemanaList() {
		for (Semana semana2 : Semana.values()) {
			semanaList.add(semana2);
		}
		return semanaList;
	}

	@Command
	@NotifyChange("fichaList")
	public void confirmar(BindContext ctx,
			@ContextParam(ContextType.VIEW) Component view) {
		
		//Response res = new Response(true, null, TypeMessage.AVISO);
		Selectors.wireEventListeners(view, this);
		
		if(getAlunoselecionada() == null){
			
			Response res = new Response(false,"Por favor selecione um aluno!", TypeMessage.AVISO) ;
			msgbox.mensagem(res.getTypeMessage(), res.getMensagem());
		}
		setWindowAluno((Window) view.getFellow("windowAluno"));
		setNomeAluno((Textbox) view.getFellow("nomeAluno"));
		getNomeAluno().setValue(getAlunoselecionada().getNome());
		getWindowAluno().setVisible(false);
		setFichaList(controller.getLstEntities(String
				.valueOf(getAlunoselecionada().getIdaluno())));
		
	}

	@Command
	public void buscarAluno(BindContext ctx,
			@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireEventListeners(view, this);
		setWindowAluno((Window) view.getFellow("windowAluno"));
		getWindowAluno().setVisible(true);
		getWindowAluno().doModal();
	}

	@Command
	@NotifyChange("alunoList")
	public List<?> listarAluno(IModel<?> imodel) {
		AlunoController alunoController = new AlunoController();
		if (keyword == null) {
			keyword = "";
		}
		System.out
				.println("Tem que devolver a lista de acordo com a keyword...");
		setAlunoList(alunoController.getLstEntities((keyword)));
		return alunoList;
	}

	@NotifyChange("serieList")
	public List<Serie> getSerieList() {
		for (Serie serie2 : Serie.values()) {
			serieList.add(serie2);
		}
		return serieList;
	}

	@Command
	@NotifyChange("fichaList")
	public Response salvar(IModel<?> imodel) {
		getEntity().setAluno(getAlunoselecionada());
		getEntity().setCategoria(getSubcategoriaselecionada());
		getEntity().setExercicio(getExercicioselecionado());
		getEntity().setSemana(String.valueOf(getSemanaselecionada().getDescricao()));
		getEntity().setSerie(getSerieselecionada().getId());
		getEntity().setAtivo(true);
		getEntity().setDatainsercao(new Date());
		super.salvar(getEntity());

		if (keyword == null) {
			keyword = "";
		}
		System.out
				.println("Tem que devolver a lista de acordo com a keyword...");
		setFichaList(controller.getLstEntities(String
				.valueOf(getAlunoselecionada().getIdaluno())));
		return null;
	}

	@Command
	@NotifyChange("fichaList")
	public Response listarFichaAluno() {
		if(Executions.getCurrent().getSession().getAttribute("status")!=null){
			if(!Executions.getCurrent().getSession().getAttribute("status").equals("onlineFuncionario")){
				AlunoController alunoController = new AlunoController();
				setAlunoselecionada(alunoController.pesquisarAlunoIdusuario(String.valueOf(Executions.getCurrent().getSession().getAttribute("id"))));
				setFichaList(controller.getLstEntities(String.valueOf(getAlunoselecionada().getIdaluno())));
			    return null;
			}
	    }
		
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	@Command
	public void gerarRelatorio(){
		
		try {
			if(getFichaList()!=null){
				super.gerarRelatorio("reportA4", getFichaList(), "fichaTreino");
				
			}else{
				Response res = new Response(false, "Não há nada na lista!", TypeMessage.ERROR);
				msgbox.mensagem(res.getTypeMessage(), res.getMensagem());
			}
			
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public FichaTreino getSelectedFichaTreino() {
		return selectedFichaTreino;
	}

	public void setSelectedFichaTreino(FichaTreino selectedFichaTreino) {
		this.selectedFichaTreino = selectedFichaTreino;
	}

	public Window getWindowAluno() {
		return windowAluno;
	}

	public void setWindowAluno(Window windowAluno) {
		this.windowAluno = windowAluno;
	}

	public void setSubcategoriaList(List<?> subcategoriaList) {
		this.subcategoriaList = subcategoriaList;
	}

	public Textbox getNomeAluno() {
		return nomeAluno;
	}

	public void setNomeAluno(Textbox nomeAluno) {
		this.nomeAluno = nomeAluno;
	}

	public Response desativar(IModel<?> imodel) {
		// TODO Auto-generated method stub
		return null;
	}

	public Exercicio getExercicioselecionado() {
		return exercicioselecionado;
	}

	public void setExercicioselecionado(Exercicio exercicioselecionado) {
		this.exercicioselecionado = exercicioselecionado;
	}

	public List<?> getMultimidiaList() {
		return multimidiaList;
	}

	public void setMultimidiaList(List<?> multimidiaList) {
		this.multimidiaList = multimidiaList;
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

	public void setExercicioList(List<?> exercicioList) {
		this.exercicioList = exercicioList;
	}

	public void setCategoriaList(List<?> categoriaList) {
		this.categoriaList = categoriaList;
	}

	public void setAlunoList(List<?> alunoList) {
		this.alunoList = alunoList;
	}

	public Semana getSemanaselecionada() {
		return semanaselecionada;
	}

	public void setSemanaselecionada(Semana semanaselecionada) {
		this.semanaselecionada = semanaselecionada;
	}

	public Serie getSerieselecionada() {
		return serieselecionada;
	}

	public void setSerieselecionada(Serie serieselecionada) {
		this.serieselecionada = serieselecionada;
	}

	public void setSemanaList(List<Semana> semanaList) {
		this.semanaList = semanaList;
	}

	public void setSerieList(List<Serie> serieList) {
		this.serieList = serieList;
	}

	public List<?> getFichaList() {
		return fichaList;
	}

	public void setFichaList(List<?> fichaList) {
		this.fichaList = fichaList;
	}

	public Categoria getSubcategoriaselecionada() {
		return subcategoriaselecionada;
	}

	public void setSubcategoriaselecionada(Categoria subcategoriaselecionada) {
		this.subcategoriaselecionada = subcategoriaselecionada;
	}

	public Vbox getVboxRelatorio() {
		return vboxRelatorio;
	}

	public void setVboxRelatorio(Vbox vboxRelatorio) {
		this.vboxRelatorio = vboxRelatorio;
	}

	public Response listar() {
		// TODO Auto-generated method stub
		return null;
	}


}
