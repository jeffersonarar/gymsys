package br.ueg.unucet.gymsys.Colecao;

import java.util.ArrayList;
import java.util.HashMap;

import br.ueg.unucet.gymsys.Controller.AlunoController;
import br.ueg.unucet.gymsys.Controller.CategoriaController;
import br.ueg.unucet.gymsys.Controller.ExercicioController;
import br.ueg.unucet.gymsys.Enum.Semana;
import br.ueg.unucet.gymsys.Enum.Serie;
import br.ueg.unucet.gymsys.Model.Aluno;
import br.ueg.unucet.gymsys.Model.Categoria;
import br.ueg.unucet.gymsys.Model.Exercicio;
import br.ueg.unucet.gymsys.Model.FichaTreino;

public class ColecaoFichaTreino {
private ArrayList<FichaTreino> fichaTreinos = new ArrayList<FichaTreino>();
	
	public  ArrayList<FichaTreino> getAll() {
        return fichaTreinos;
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
				
				if(hashMap.get("idexercicio") != null){
					exercicio = exercicioController.getEntity((String) hashMap.get("idexercicio"));
				}
				if(hashMap.get("idcategoria") != null){
					categoria = categoriaController.getEntity((String) hashMap.get("idcategoria"));
				}
				if(hashMap.get("idaluno") != null){
					aluno = alunoController.getEntity((String) hashMap.get("idaluno"));
				}
				
				FichaTreino fichaTreino = new FichaTreino(
						Integer.parseInt((String) hashMap.get("idfichatreino")),
						aluno,
						categoria, 
						exercicio, 
						(String) hashMap.get("semana"), 
						Integer.parseInt((String) hashMap.get("serie")), 
						(String) hashMap.get("repeticao"), 
						(hashMap.get("ativo").equals("t")));
				fichaTreinos.add(fichaTreino);	
			}		
		}
    }

	public br.ueg.unucet.gymsys.Model.FichaTreino getIndice(int indice) {
		   return fichaTreinos.get(indice);
	}	
}
