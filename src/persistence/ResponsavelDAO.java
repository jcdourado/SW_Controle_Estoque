package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Responsavel;

public class ResponsavelDAO {
	private Connection c;
	
	public ResponsavelDAO() {
		GenericDAO gen = new GenericDAO();
		try {
			c = gen.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void adicionar(Responsavel e) {
		try {
			String sql = "INSERT INTO responsavel (nome, telefone) VALUES (?, ?)";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, e.getNome());
			ps.setString(2, e.getTel());
			ps.execute();
			ps.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public void atualizar(Responsavel e) {
		try {
			String sql = "UPDATE responsavel " + 
				     " SET nome = ?, telefone = ? WHERE codResponsavel = ? ";
			PreparedStatement ps = c.prepareStatement( sql );
			ps.setString(1, e.getNome());
			ps.setString(2, e.getTel());
			ps.setInt(3, e.getId());
			ps.execute();
			ps.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public void remover(int cod) {
		try {
			String sql = "DELETE FROM responsavel WHERE codResponsavel = ? ";
			PreparedStatement ps = c.prepareStatement( sql );
			ps.setLong(1, cod);
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}