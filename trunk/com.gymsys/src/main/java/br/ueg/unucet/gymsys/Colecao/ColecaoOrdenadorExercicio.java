package br.ueg.unucet.gymsys.Colecao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import br.ueg.unucet.gymsys.Controller.CategoriaController;
import br.ueg.unucet.gymsys.Controller.MultimidiaController;
import br.ueg.unucet.gymsys.DAO.MultimidiaDAO;
import br.ueg.unucet.gymsys.Model.Categoria;
import br.ueg.unucet.gymsys.Model.Exercicio;
import br.ueg.unucet.gymsys.Model.Multimidia;
import br.ueg.unucet.gymsys.Model.OrdenadorExercicio;

public class ColecaoOrdenadorExercicio {
	private ArrayList<OrdenadorExercicio> listaOrdenadorExercicios = new ArrayList<OrdenadorExercicio>();
	
	public  ArrayList<OrdenadorExercicio> getAll() {
        return listaOrdenadorExercicios;
    }
	
	public  void setAll(ArrayList<Exercicio> result) {
		if(result != null){
			int indice = 0;
			MultimidiaController mController = new MultimidiaController();
			Multimidia midia = null;
			MultimidiaController midiaController = new MultimidiaController();
			while(indice < result.size()){
				OrdenadorExercicio ordenadorExercicio = new OrdenadorExercicio();
				ordenadorExercicio.setExercicio(result.get(indice));
				Exercicio auxExercicio = result.get(indice);
				midia = (Multimidia) midiaController.buscarImagemExercicio(auxExercicio.getIdexercicio());
				ordenadorExercicio.setImagem(midia);
				try {
					midia = (Multimidia) midiaController.buscarVideoExercicio(auxExercicio.getIdexercicio());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ordenadorExercicio.setVideo(midia);
				listaOrdenadorExercicios.add(ordenadorExercicio);
				indice++;
			}
				
		}
    }

	public OrdenadorExercicio getIndice(int indice) {
		if(listaOrdenadorExercicios.isEmpty()) {
			return null;
		}
		   return listaOrdenadorExercicios.get(indice);
	}
}