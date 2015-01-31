package br.ueg.unucet.gymsys.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import br.ueg.unucet.gymsys.Connection.Connect;
import br.ueg.unucet.gymsys.Model.IModel;
import br.ueg.unucet.gymsys.Model.Multimidia;

public class MultimidiaDAO extends GenericDAO {

	
	public String  pesquisarProximoID() throws SQLException{
					
			 String sql =" SELECT idmultimidia "
			 		+ "FROM multimidia order by idmultimidia desc  LIMIT 1;";
	
			if(Connect.getConexao()){
		
				ResultSet rs =  Connect.setResultSet(sql);
				int colCount = rs.getMetaData().getColumnCount();
				String valor = null;
				if(rs.next()){
					valor	= rs.getString("idmultimidia");
					
				}
				Connect.close();
				if(valor == null){
					return "0";
				}
				Integer resultado = (Integer.valueOf(valor))+1;
		
				return String.valueOf(resultado);
			}
			System.out.println("Erro ao pesquisar proximoID da multimidia");
			return null;
	}
	
public ArrayList<HashMap<String,Object>>  pesquisarImagemPorExercicio(IModel<?> entidade, int criterio) throws SQLException{
		
		String sql = "select "+ entidade.getTableColumnNames()+" from " + entidade.getTableName() + " ";
		sql = sql + " where ativo = 'true' and " + "idexercicio" +" = " +criterio + "  and tipomultimidia = 'true' "+ " order by " + entidade.getOrdenacao() + " ;";
		
		
		System.out.println("sql pesquisarCriterio :"+sql);
		if(Connect.getConexao()){
			
			ArrayList<HashMap<String,Object>> result = new ArrayList<HashMap<String,Object>>();
			
		ResultSet rs =  Connect.setResultSet(sql);
		int colCount = rs.getMetaData().getColumnCount();
		while(rs.next()){
				HashMap<String,Object> record = new HashMap<String, Object>();
				for(int i=1;i<=colCount;i++){
					record.put(rs.getMetaData().getColumnName(i), rs.getString(i));
				}
				result.add(record);
		}
		Connect.close();
		return result;
		}
		System.out.println("O " + entidade.getTableName() + " Não Foi pesquisado problema no DAOUsuario pesquisarusuario(String) Connect.getConexão");
		return null;
	}

public ArrayList<HashMap<String, Object>> pesquisarVideoPorExercicio(
		Multimidia multimidia, int idexercicio) {

	String sql = "select "+ multimidia.getTableColumnNames()+" from " + multimidia.getTableName() + " ";
	sql = sql + " where ativo = 'true' and " + "idexercicio" +" = " +idexercicio + "  and tipomultimidia = 'false' "+ " order by " + multimidia.getOrdenacao() + " ;";
	
	
	System.out.println("sql pesquisarCriterio :"+sql);
	if(Connect.getConexao()){
		
		ArrayList<HashMap<String,Object>> result = new ArrayList<HashMap<String,Object>>();
		
	ResultSet rs =  Connect.setResultSet(sql);
	int colCount = 0;
	try {
		colCount = rs.getMetaData().getColumnCount();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	try {
		while(rs.next()){
				HashMap<String,Object> record = new HashMap<String, Object>();
				for(int i=1;i<=colCount;i++){
					record.put(rs.getMetaData().getColumnName(i), rs.getString(i));
				}
				result.add(record);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	Connect.close();
	return result;
	}
	System.out.println("O " + multimidia.getTableName() + " Não Foi pesquisado problema no DAOUsuario pesquisarusuario(String) Connect.getConexão");
	return null;
}
}
	

