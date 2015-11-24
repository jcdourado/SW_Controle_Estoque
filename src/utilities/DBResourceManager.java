package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBResourceManager {
	private static String DRIVER = "net.sourceforge.jtds.jdbc.Driver";
	private static String URL = "jdbc:jtds:sqlserver://127.0.0.1:1433;DatabaseName=estoque;namedPipe=true";
	private static String USER = "sa";
	private static String PASSWORD = "1408";
	private static DBResourceManager instancia;
	private Connection con;
	
	private DBResourceManager() throws ClassNotFoundException, SQLException{
		Class.forName(DRIVER);
		con = DriverManager.getConnection(URL, USER, PASSWORD);
	}
	
	public static DBResourceManager getRecource() throws ClassNotFoundException, SQLException{
		if(instancia == null){
			return (new DBResourceManager());
		}
		return instancia;
	}
	
	public Connection getConnection(){
		return con;
	}
}
