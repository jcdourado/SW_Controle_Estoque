package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Tipo;

public class RestricaoDAO {
	
	private Connection c;
	public RestricaoDAO() {
		GenericDAO gen = new GenericDAO();
		try {
			c = gen.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void adicionar(Tipo e, Tipo e2) {
		try {
			String sql = "INSERT INTO tipoRestricao (codTipo, codRestTipo_FK) VALUES (?, ?)";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, e.getId());
			ps.setInt(2, e2.getId());
			ps.execute();
			ps.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public void remove(Tipo e1, Tipo e2) {
		try {
			String sql = "DELETE FROM tipoRestricao WHERE codTipo = ? AND codRestTipo_FK = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, e1.getId());
			ps.setInt(2, e2.getId());
			ps.execute();
			ps.close();
		} catch (SQLException expection) {
			expection.printStackTrace();
		}
	}
}
