package br.ueg.unucet.gymsys.Colecao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import br.ueg.unucet.gymsys.Controller.AlunoController;
import br.ueg.unucet.gymsys.Controller.UsuarioController;
import br.ueg.unucet.gymsys.Model.Aluno;
import br.ueg.unucet.gymsys.Model.Usuario;



public class ColecaoAluno {
	private ArrayList<Aluno> alunos = new ArrayList<Aluno>();

	public  ArrayList<Aluno> getAll() {
        return alunos;
    }
	
	Date data;
	Date date;
	
	public  void setAll(ArrayList<HashMap<String,Object>> result) {
		AlunoController alunoController = new AlunoController();
		Date dataNascimento = null;
		Usuario usuario = null;
		if(result != null){

			for (HashMap<String, Object> hashMap : result) {
				dataNascimento = null;
				usuario = null;
				if(hashMap.get("data")!=  null){
					DateFormat out = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
					SimpleDateFormat formato2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
				   // data = new Date();
					try {
					//	Date formattedDate = out.parse(out.format()));
						data =  (java.util.Date) out.parse((String) hashMap.get("data").toString());
						//s data1 = formato2.format(data); 
						// data1 = new java.sql.Date(formato2.parse(data).getTime());
						 date = (Date)formato2.parse(formato2.format(data));  
						///s	data = out.parse(.toString());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				if(hashMap.get("datanascimento")!=  null){
					DateFormat out = new SimpleDateFormat("yyyy-MM-dd"); 
					SimpleDateFormat formato2 = new SimpleDateFormat("dd/MM/yyyy");  
				   // data = new Date();
					try {
					//	Date formattedDate = out.parse(out.format()));
						dataNascimento =  (java.util.Date) out.parse((String) hashMap.get("datanascimento").toString());
						//s data1 = formato2.format(data); 
						// data1 = new java.sql.Date(formato2.parse(data).getTime());
						dataNascimento = (Date)formato2.parse(formato2.format(dataNascimento));  
						///s	data = out.parse(.toString());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				if(hashMap.get("idusuario")!=  null){
					UsuarioController controleUsuario = new UsuarioController();
					usuario = controleUsuario.getEntity((String)hashMap.get("idusuario"));
				}
				Integer idalunoalteracao;
				if(hashMap.get("idalunoalteracao") != null){
					idalunoalteracao = Integer.parseInt((String) hashMap.get("idalunoalteracao"));
				}else{
					idalunoalteracao = 0;
				}
				
				Aluno aluno = new Aluno();
				if((String) hashMap.get("abdomen")!= null){
					aluno.setAbdomen(Double.valueOf((String) hashMap.get("abdomen")));
				}
				if((String) hashMap.get("altura")!= null){
					aluno.setAltura(Double.valueOf((String) hashMap.get("altura")));
				}
				if((String) hashMap.get("antebraco")!= null){
					aluno.setAntebraco(Double.valueOf((String) hashMap.get("antebraco")));
				}
				
				if((String) hashMap.get("celular")!= null){
					aluno.setCelular(Integer.valueOf((String) hashMap.get("celular")));
				}
				
				if((String) hashMap.get("cintura")!= null){
					aluno.setCintura(Double.valueOf((String) hashMap.get("cintura")));
				}
				
				if((String) hashMap.get("ombro")!= null){
					aluno.setOmbro(Double.valueOf((String) hashMap.get("ombro")));
				}
				
				if((String) hashMap.get("pescoco")!= null){
					aluno.setPescoco(Double.valueOf((String) hashMap.get("pescoco")));
				}
				
				if((String) hashMap.get("coxa")!= null){
					aluno.setCoxa(Double.valueOf((String) hashMap.get("coxa")));
				}
				
				if((String) hashMap.get("idaluno")!= null){
					aluno.setIdaluno(Integer.valueOf((String) hashMap.get("idaluno")));
				}
				
				if((String) hashMap.get("peso")!= null){
					aluno.setPeso(Double.valueOf((String) hashMap.get("peso")));
				}
				
				if((String) hashMap.get("torax")!= null){
					aluno.setTorax(Double.valueOf((String) hashMap.get("torax")));
				}
				
				aluno.setAtivo((hashMap.get("ativo").equals("t")));
				aluno.setCpf((String) hashMap.get("cpf"));
				aluno.setData(data);
				aluno.setDatanascimento(dataNascimento);
				aluno.setNome((String) hashMap.get("nome"));
				aluno.setUsuario(usuario);
				alunos.add(aluno);
			}
				
				
		}
    }

	public br.ueg.unucet.gymsys.Model.Aluno getIndice(int indice) {
			if(alunos.isEmpty()) return null;
		   return alunos.get(indice);
	}
}



