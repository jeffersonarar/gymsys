package br.ueg.unucet.gymsys.Colecao;


import java.util.ArrayList;
import java.util.HashMap;

import br.ueg.unucet.gymsys.Controller.AlunoController;
import br.ueg.unucet.gymsys.Controller.CategoriaController;
import br.ueg.unucet.gymsys.Controller.ExercicioController;
import br.ueg.unucet.gymsys.Model.Aluno;
import br.ueg.unucet.gymsys.Model.Categoria;
import br.ueg.unucet.gymsys.Model.Exercicio;
import br.ueg.unucet.gymsys.Model.Multimidia;


public class ColecaoMultimidia {
	private ArrayList<Multimidia> multimidias = new ArrayList<Multimidia>();
	
	public  ArrayList<Multimidia> getAll() {
        return multimidias;
    }
	
	public  void setAll(ArrayList<HashMap<String,Object>> result) {
		CategoriaController categoriaController = new CategoriaController();
		ExercicioController exercicioController = new ExercicioController();
		AlunoController alunoController = new AlunoController();
		
		if(result != null){
			for (HashMap<String, Object> hashMap : result) {
				Exercicio exercicio = null;
				Categoria categoria = null; 
				Aluno aluno = null;
				
//	
				String stream = (String) hashMap.get("multimidia");
				if(hashMap.get("idexercicio") != null){
					exercicio = exercicioController.getEntity((String) hashMap.get("idexercicio"));
				}
				if(hashMap.get("idcategoria") != null){
					categoria = categoriaController.getEntity((String) hashMap.get("idcategoria"));
				}
				if(hashMap.get("idaluno") != null){
					aluno = alunoController.getEntity((String) hashMap.get("idaluno"));
				}
				
				 Multimidia multimidia = new Multimidia(Integer.parseInt((String) hashMap.get("idmultimidia")), 
						(String) hashMap.get("nome"),
						(String) hashMap.get("descricao"),
						stream, 
						 exercicio,
						 categoria,
						 aluno,
						(hashMap.get("tipomultimidia").equals("f")),
						(hashMap.get("ativo").equals("t"))
						);
				multimidias.add(multimidia);	
			}		
		}
    }

	public br.ueg.unucet.gymsys.Model.Multimidia getIndice(int indice) {
		if(multimidias.isEmpty()){
			return null;
		}
		   return multimidias.get(indice);
	}	
}
