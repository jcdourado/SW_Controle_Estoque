package persistence;
						
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import utilities.EstoqueException;

public class GenericDAO {

private static Connection con;

	public Connection getConnection() throws EstoqueException { 
	
		try { 
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:jtds:sqlserver://127.0.0.1:1433;DatabaseName=estoque;namedPipe=true","sa","1408");
			System.out.println("Conexao ok");
		} catch (ClassNotFoundException e) {
			throw new EstoqueException(e);
		} catch (SQLException e) {
			throw new EstoqueException(e);
		} catch (Exception e) { 
			throw new EstoqueException(e);
		}
		return con; 
	}


	public void fechaConexao() throws EstoqueException{
		try {
			if(con!=null) con.close();
				con =null;
			} catch(Exception e) {
				throw new EstoqueException(e);
			}
	}
}


