package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.SolicitacaoProdutoDepartamento;

public class SolicitacaoProdutoDepartamentoDAO {
	private Connection c;
	public SolicitacaoProdutoDepartamentoDAO() {
		GenericDAO gen = new GenericDAO();
		try {
			c = gen.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void adicionar(SolicitacaoProdutoDepartamento e) {
		try {
			String sql = "INSERT INTO solicitacao_Produto_Departamento (id_Produto, "
					+ "id_Solicitacao, quantidade) VALUES (?, ?, ?)";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, e.getIdProduto());
			ps.setInt(2, e.getIdSolicitacao());
			ps.setFloat(3, e.getQuantidade());
			ps.execute();
			ps.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public void atualizar(SolicitacaoProdutoDepartamento e) {
		try {
			String sql = "UPDATE solicitacao_Produto_Departamento SET quantidade = ? WHERE id_Produto = ? AND id_Solicitacao = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setFloat(1, e.getQuantidade());
			ps.setInt(2, e.getIdProduto());
			ps.setInt(3, e.getIdSolicitacao());
			ps.execute();
			ps.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public void remove(SolicitacaoProdutoDepartamento e) {
		try {
			String sql = "DELETE FROM solicitacao_Produto_Departamento WHERE id_Produto = ? AND id_Solicitacao = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, e.getIdProduto());
			ps.setInt(2, e.getIdSolicitacao());
			ps.execute();
			ps.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}
