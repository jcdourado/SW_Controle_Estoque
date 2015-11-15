package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Item;

public class ItemDAO {
private Connection c;
	
	public ItemDAO() {
		GenericDAO gen = new GenericDAO();
		try {
			c = gen.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void adicionar(Item e) {
		try {
			String sql = "INSERT INTO item (codItem, codProduto, codSaida, codEntrada) VALUES (?, ?, ?, ?)";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, e.getIdItem());
			ps.setInt(2, e.getIdProduto());
			ps.setInt(3, e.getIdSaida());
			ps.setInt(4, e.getIdEntrada());
			ps.execute();
			ps.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public void atualizar(int cod, Item e) {
		try {
			String sql = "UPDATE item SET codProduto = ?, codSaida = ?, codEntrada = ? WHERE codItem = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, e.getIdProduto());
			ps.setInt(2, e.getIdSaida());
			ps.setInt(3, e.getIdEntrada());
			ps.setInt(4, cod);
			ps.execute();
			ps.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public void remove(int cod) {
		try {
			String sql = "DELETE FROM item WHERE codItem = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, cod);
			ps.execute();
			ps.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}
