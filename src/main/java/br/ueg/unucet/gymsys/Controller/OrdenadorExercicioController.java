package br.ueg.unucet.gymsys.Controller;

import java.sql.SQLException;
import java.util.List;

import br.ueg.unucet.gymsys.Colecao.ColecaoCategoria;
import br.ueg.unucet.gymsys.Colecao.ColecaoExercicio;
import br.ueg.unucet.gymsys.Colecao.ColecaoOrdenadorExercicio;
import br.ueg.unucet.gymsys.DAO.ExercicioDAO;
import br.ueg.unucet.gymsys.Enum.TypeMessage;
import br.ueg.unucet.gymsys.Model.Exercicio;
import br.ueg.unucet.gymsys.Model.IModel;
import br.ueg.unucet.gymsys.Model.Model;
import br.ueg.unucet.gymsys.Model.OrdenadorExercicio;
import br.ueg.unucet.gymsys.Model.Usuario;
import br.ueg.unucet.gymsys.Util.Mensagembox;
import br.ueg.unucet.gymsys.Util.Response;

public class OrdenadorExercicioController extends GenericController<OrdenadorExercicio> {
	
		private OrdenadorExercicio ordenadorExercicio = new OrdenadorExercicio();
		
		@Override
		public List<?> getLstEntities(String keyword) {
			ColecaoExercicio listaExercicio = new ColecaoExercicio();
			Exercicio exercicio = new Exercicio();
			try {
				listaExercicio.setAll(dao.pesquisarNome(exercicio, keyword));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ColecaoOrdenadorExercicio colecaoOrdenar = new ColecaoOrdenadorExercicio();
			colecaoOrdenar.setAll(listaExercicio.getAll());
			return colecaoOrdenar.getAll();
		}
		
		public List<?> getLstCriteria(String keyword) {
			ColecaoExercicio listaExercicio = new ColecaoExercicio();
			Exercicio exercicio = new Exercicio();
			try {
				listaExercicio.setAll(dao.pesquisarCriterio(exercicio, Integer.parseInt(keyword)));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ColecaoOrdenadorExercicio colecaoOrdenar = new ColecaoOrdenadorExercicio();
			colecaoOrdenar.setAll(listaExercicio.getAll());
			return colecaoOrdenar.getAll();
		}
		
		@Override
		public List<?> getLstEntitiesAtivos(String keyword) {
			ColecaoExercicio listaExercicio = new ColecaoExercicio();
			Exercicio exercicio = new Exercicio();
			try {
				listaExercicio.setAll(dao.pesquisarNomeAtivo(exercicio, keyword));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ColecaoOrdenadorExercicio colecaoOrdenar = new ColecaoOrdenadorExercicio();
			colecaoOrdenar.setAll(listaExercicio.getAll());
			return colecaoOrdenar.getAll();
		}
		
		public OrdenadorExercicio getEntity(String id) {
			Exercicio exercicio = new Exercicio();
			exercicio.setIdexercicio(Integer.parseInt(id));
			ColecaoExercicio listaExercicio = new ColecaoExercicio();
			try {
				listaExercicio.setAll(dao.pesquisarID(exercicio));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ColecaoOrdenadorExercicio colecaoOrdenar = new ColecaoOrdenadorExercicio();
			colecaoOrdenar.setAll(listaExercicio.getAll());
			return ((ColecaoOrdenadorExercicio) colecaoOrdenar).getIndice(0);
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

		public List<?> getListarExercicioPorCategoria(int idCategoria) {
			ColecaoExercicio listaExercicio = new ColecaoExercicio();
			Exercicio exercicio = new Exercicio();
			ExercicioDAO exercicioDao = new ExercicioDAO();
			try {
				listaExercicio.setAll(exercicioDao.pesquisarExercicioCategoria(exercicio, idCategoria));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ColecaoOrdenadorExercicio colecaoOrdenar = new ColecaoOrdenadorExercicio();
			colecaoOrdenar.setAll(listaExercicio.getAll());
			return colecaoOrdenar.getAll();
		}		
	}