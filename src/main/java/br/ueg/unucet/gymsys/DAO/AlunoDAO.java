package br.ueg.unucet.gymsys.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import br.ueg.unucet.gymsys.Connection.Connect;
import br.ueg.unucet.gymsys.Model.IModel;

public class AlunoDAO extends GenericDAO {

	
public ArrayList<HashMap<String,Object>>  pesquisarHistorico(IModel<?> entidade, int criterio) throws SQLException{
				
		 String sql =" select * from historicoaluno  WHERE  idaluno = " + criterio 
		 		+" order by data desc;";
		
		
		System.out.println("sql:"+sql);
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
}
	

