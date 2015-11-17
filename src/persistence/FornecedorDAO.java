package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Fornecedor;

public class FornecedorDAO {
	private Connection c;
	
	public FornecedorDAO() {
		GenericDAO gen = new GenericDAO();
			c = gen.getConnection();
	}
	
	public void adicionar(Fornecedor e) {
		try {
			String sql = "INSERT INTO fornecedor (rua, numero, bairro, "
					+ "cidade, estado, nome, telefone) VALUES (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, e.getRua());
			ps.setInt(2, e.getNumero());
			ps.setString(3, e.getBairro());
			ps.setString(4, e.getCidade());
			ps.setString(5, e.getEstado());
			ps.setString(6, e.getNome());
			ps.setString(7, e.getTel());
			ps.execute();
			ps.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public void atualizar(int cod, Fornecedor e) {
		try {
			String sql = "UPDATE fornecedor SET rua = ?, numero = ?, bairro = ?, cidade = ?, "
					+ "estado = ?, nome = ?, telefone = ? WHERE codFornecedor = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, e.getRua());
			ps.setInt(2, e.getNumero());
			ps.setString(3, e.getBairro());
			ps.setString(4, e.getCidade());
			ps.setString(5, e.getEstado());
			ps.setString(6, e.getNome());
			ps.setString(7, e.getTel());
			ps.setInt(8, e.getId());
			ps.execute();
			ps.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public void remove(int cod) {
		try {
			String sql = "DELETE FROM fornecedor WHERE codFornecedor = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, cod);
			ps.execute();
			ps.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	public int proximoId() throws SQLException {
		String sql = "SELECT MAX(codFornecedor) + 1 AS proximo_id FROM fornecedor";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		if (rs.next()){
			return rs.getInt("proximo_id");
		} else {
			return 1;
		}
	}	
}
