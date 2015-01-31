package br.ueg.unucet.gymsys.Colecao;

import java.util.ArrayList;
import java.util.HashMap;

import br.ueg.unucet.gymsys.Controller.CategoriaController;
import br.ueg.unucet.gymsys.Model.Categoria;
import br.ueg.unucet.gymsys.Model.Exercicio;;

public class ColecaoExercicio {
	private ArrayList<Exercicio> listaExercicios = new ArrayList<Exercicio>();
	
	public  ArrayList<Exercicio> getAll() {
        return listaExercicios;
    }
	
	public  void setAll(ArrayList<HashMap<String,Object>> result) {
		if(result != null){
			CategoriaController categoriaController = new CategoriaController();
			Categoria categoria = null;
			
			for (HashMap<String, Object> hashMap : result) {
				categoria = categoriaController.getEntity((String) hashMap.get("idcategoria"));
				Exercicio exercicio = new Exercicio(	Integer.parseInt((String) hashMap.get("idexercicio")),
												(String) hashMap.get("nome"),
												categoria,
												(String) hashMap.get("responsavel"),
												(String) hashMap.get("descricao"),
												(hashMap.get("ativo").equals("t")));
				listaExercicios.add(exercicio);
			}
				
		}
    }

	public br.ueg.unucet.gymsys.Model.Exercicio getIndice(int indice) {
		   return listaExercicios.get(indice);
	}
}