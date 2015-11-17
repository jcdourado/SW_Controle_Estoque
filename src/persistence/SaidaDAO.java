package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Saida;

public class SaidaDAO {
	private Connection c;
	
	public SaidaDAO() {
		GenericDAO gen = new GenericDAO();
		try {
			c = gen.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void adicionar(Saida e) {
		try {
			String sql = "INSERT INTO saida (data, descricao) VALUES (?, ?)";
			PreparedStatement ps = c.prepareStatement(sql);
			java.sql.Date sd = new java.sql.Date( e.getData().getTime() );
			ps.setDate(1, sd);
			ps.setString(2, e.getDescricao());
			ps.execute();
			ps.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
	}

	public void atualizar( Saida e) {
		try {
			String sql = "UPDATE saida " + 
				     " SET data = ?, descricao = ? " + 
				     " WHERE codSaida = ? ";
			PreparedStatement ps = c.prepareStatement( sql );
			java.sql.Date sd = new java.sql.Date( e.getData().getTime() );
			ps.setInt(3, e.getIdSaida());
			ps.setDate(1, sd);
			ps.setString(2, e.getDescricao());
			ps.execute();
			ps.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public void remover(int cod) {
		try {
			String sql = "DELETE FROM saida WHERE codSaida = ? ";
			PreparedStatement ps = c.prepareStatement( sql );
			ps.setLong(1, cod);
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
