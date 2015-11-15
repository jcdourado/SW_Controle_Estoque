package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GenericDAO {
	private static String JTDS_DRIVER = "net.sourceforge.jtds.jdbc.Driver";
	private static String JDBC_URL = "jdbc:jtds:sqlserver://localhost:1433/estoque";
	private static String USER = "sa";
	private static String PASSWORD = "1408";
	
	public Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName( JTDS_DRIVER );
		return DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
	}
}