package br.ueg.unucet.gymsys.Colecao;

import java.util.ArrayList;
import java.util.HashMap;

import br.ueg.unucet.gymsys.Controller.CategoriaController;
import br.ueg.unucet.gymsys.Model.Categoria;

public class ColecaoCategoria {
	private ArrayList<Categoria> listaCategorias = new ArrayList<Categoria>();
	
	public  ArrayList<Categoria> getAll() {
        return listaCategorias;
    }
	
	public  void setAll(ArrayList<HashMap<String,Object>> result) {
		CategoriaController categoriaController = new CategoriaController();
		if(result != null){
			for (HashMap<String, Object> hashMap : result) {
				Categoria aux;
				if((String) hashMap.get("supcategoria")==null){
					aux = null;
				}else{
					aux = categoriaController.getEntity((String) hashMap.get("supcategoria"));
				}
				Categoria categoria = new Categoria(	Integer.parseInt((String) hashMap.get("idcategoria")),
												(String) hashMap.get("nome"),
												aux,
												(String) hashMap.get("descricao"),
												(hashMap.get("ativo").equals("t")));
				listaCategorias.add(categoria);
			}
		}
    }

	public br.ueg.unucet.gymsys.Model.Categoria getIndice(int indice) {
		if(listaCategorias.isEmpty()){
			return null;
		}
		   return listaCategorias.get(indice);
	}
}