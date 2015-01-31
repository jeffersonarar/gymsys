package br.ueg.unucet.gymsys.Controller;

import java.sql.SQLException;
import java.util.List;

import br.ueg.unucet.gymsys.Colecao.ColecaoCategoria;
import br.ueg.unucet.gymsys.Colecao.ColecaoExercicio;
import br.ueg.unucet.gymsys.Enum.TypeMessage;
import br.ueg.unucet.gymsys.Model.Exercicio;
import br.ueg.unucet.gymsys.Model.IModel;
import br.ueg.unucet.gymsys.Model.Model;
import br.ueg.unucet.gymsys.Model.Usuario;
import br.ueg.unucet.gymsys.Util.Mensagembox;
import br.ueg.unucet.gymsys.Util.Response;

public class ExercicioController extends GenericController<Exercicio> {
	
		private Exercicio exercicio = new Exercicio();
		
		@Override
		public List<?> getLstEntities(String keyword) {
			ColecaoExercicio listaExercicio = new ColecaoExercicio();
			try {
				listaExercicio.setAll(dao.pesquisarNome(exercicio, keyword));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return listaExercicio.getAll();
		}
		
		public List<?> getLstCriteria(String keyword) {
			ColecaoExercicio listaExercicio = new ColecaoExercicio();
			try {
				listaExercicio.setAll(dao.pesquisarCriterio(exercicio, Integer.parseInt(keyword)));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return listaExercicio.getAll();
		}
		
		@Override
		public List<?> getLstEntitiesAtivos(String keyword) {
			ColecaoExercicio listaExercicio = new ColecaoExercicio();
			try {
				listaExercicio.setAll(dao.pesquisarNomeAtivo(exercicio, keyword));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return listaExercicio.getAll();
		}
		

		public Exercicio getExercicio() {
			return this.exercicio;
		}

		public void setExercicio(Exercicio exercicio) {
			this.exercicio = exercicio;
		}
		
		public Exercicio getEntity(String id) {
			Exercicio exercicio = new Exercicio();
			exercicio.setIdexercicio(Integer.parseInt(id));
			ColecaoExercicio listaExercicio = new ColecaoExercicio();
			try {
				listaExercicio.setAll(dao.pesquisarID(exercicio));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			exercicio = ((ColecaoExercicio) listaExercicio).getIndice(0);
			return exercicio;
		}
		
		

		@Override
		public Response validar(IModel<?> imodel) {
		setExercicio((br.ueg.unucet.gymsys.Model.Exercicio) imodel);
			
			Response res = new Response(true,"", null);
			if(getExercicio().getIdcategoria() == null){
				return  new Response(false, "Campo obrigatório não preenchido.", TypeMessage.AVISO);
			}
			if(getExercicio().getNome() == null){
				return  new Response(false, "Campo obrigatório não preenchido.", TypeMessage.AVISO);
			}
			if(getExercicio().getResponsavel()== null){
				return  new Response(false, "Campo obrigatório não preenchido.", TypeMessage.AVISO);
			}
			if(getExercicio().getDescricao()== null){
				return  new Response(false, "Campo obrigatório não preenchido.", TypeMessage.AVISO);
			}
			if(getExercicio().getDescricao().length()>499){
				return  new Response(false, "Campo Descrição deve conter menos de 500 caracteres!\n\n"
											+ "No momento esta com "+ getExercicio().getDescricao().length()
											+" caracteres.", TypeMessage.AVISO);
			}
			if(getExercicio().getNome().length()<3 ||  getExercicio().getNome().length()>120){
				return  new Response(false, "Tamanho do nome informado não é adequado o nome deve conter no mínimo 3 \n no máximo 120 caracteres. Exercício não foi Salvo...", TypeMessage.AVISO);
			}
			//“Exercício já cadastrado no sistema...”
			return res;
		}	
		
		@Override
		public Response validarItemUnico(IModel<?> imodel) {
			Response res = new Response(true,"", null);
			return res;
		}
	}