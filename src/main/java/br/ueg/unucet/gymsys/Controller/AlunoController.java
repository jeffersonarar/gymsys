package br.ueg.unucet.gymsys.Controller;

import java.sql.SQLException;
import java.util.List;

import br.ueg.unucet.gymsys.Colecao.ColecaoAluno;
import br.ueg.unucet.gymsys.Colecao.ColecaoExercicio;
import br.ueg.unucet.gymsys.DAO.AlunoDAO;
import br.ueg.unucet.gymsys.Enum.TypeMessage;
import br.ueg.unucet.gymsys.Model.Aluno;
import br.ueg.unucet.gymsys.Model.IModel;
import br.ueg.unucet.gymsys.Model.Model;
import br.ueg.unucet.gymsys.Util.Mensagembox;
import br.ueg.unucet.gymsys.Util.Response;

public class AlunoController extends GenericController<Aluno>{

	@SuppressWarnings("unused")
	private Mensagembox msgbox = new Mensagembox();
	
	private Aluno Aluno = new Aluno();
	
	@Override
	public List<?> getLstEntities(String keyword) {
		Aluno aluno = new Aluno();
		ColecaoAluno clAluno = new ColecaoAluno();
		try {
			clAluno.setAll(dao.pesquisarNome(aluno, keyword));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return clAluno.getAll();
	}
	
	@Override
	public List<?> getLstEntitiesAtivos(String keyword) {
		Aluno aluno = new Aluno();
		ColecaoAluno clAluno = new ColecaoAluno();
		try {
			clAluno.setAll(dao.pesquisarNomeAtivo(aluno, keyword));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return clAluno.getAll();
	}

	public List<?> getLstCriteria(String keyword) {
		ColecaoAluno listaAluno = new ColecaoAluno();
		AlunoDAO alunoDAO = new AlunoDAO();
		try {
			listaAluno.setAll(alunoDAO.pesquisarHistorico(Aluno, Integer.parseInt(keyword)));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaAluno.getAll();
	}
	
	public Aluno getAluno() {
		return Aluno;
	}

	public void setAluno(Aluno aluno) {
		Aluno = aluno;
	}
	
	public Aluno getEntity(String id) {
		Aluno aluno = new Aluno();
		aluno.setIdaluno(Integer.parseInt(id));
		ColecaoAluno clAluno = new ColecaoAluno();
		try {
			clAluno.setAll(dao.pesquisarID(aluno));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		aluno = ((ColecaoAluno) clAluno).getIndice(0);
		return aluno;
	}



	@Override
	public Response validar(IModel<?> imodel) {
		setAluno((br.ueg.unucet.gymsys.Model.Aluno) imodel);
		Response res = new Response(true,"", null);
		return res;
	}

	@Override
	public Response validarItemUnico(IModel<?> imodel) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Aluno pesquisarAlunoIdusuario(String idusuario) {
		Aluno aluno = new Aluno();
		ColecaoAluno clAluno = new ColecaoAluno();
		try {
			clAluno.setAll(dao.pesquisarCriterio(aluno, Integer.parseInt(idusuario)));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		aluno = ((ColecaoAluno) clAluno).getIndice(0);
		return aluno;
	}



}	

