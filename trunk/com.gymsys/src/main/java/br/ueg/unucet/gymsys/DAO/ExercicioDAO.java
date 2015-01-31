package br.ueg.unucet.gymsys.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import br.ueg.unucet.gymsys.Connection.Connect;
import br.ueg.unucet.gymsys.Model.Exercicio;
import br.ueg.unucet.gymsys.Model.IModel;
import br.ueg.unucet.gymsys.Model.Multimidia;

public class ExercicioDAO extends GenericDAO {

	public ArrayList<HashMap<String, Object>> pesquisarExercicioCategoria(
		Exercicio exercicio, int idCategoria) throws SQLException {
		String sql =	" SELECT e.idexercicio, e.nome, e.idcategoria, e.responsavel, e.descricao, e.ativo " +
				" FROM exercicio as e, categoria as c " +
				" where e.idcategoria = c.idcategoria and (e.idcategoria = " + idCategoria  +" or c.supcategoria = " + idCategoria  + " ) order by e.idexercicio ; ";
	
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
	System.out.println("O " + exercicio.getTableName() + " Não Foi pesquisado problema no DAOUsuario pesquisarusuario(String) Connect.getConexão");
	return null;
}
}
	

