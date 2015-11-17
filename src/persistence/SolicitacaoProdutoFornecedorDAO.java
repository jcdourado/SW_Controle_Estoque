package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.SolicitacaoProdutoFornecedor;

public class SolicitacaoProdutoFornecedorDAO {
	private Connection c;
	public SolicitacaoProdutoFornecedorDAO() {
		GenericDAO gen = new GenericDAO();
		try {
			c = gen.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void adicionar(SolicitacaoProdutoFornecedor e) {
		try {
			String sql = "INSERT INTO solicitacao_Produto_Fornecedor (idProduto, "
					+ "idSolicitacao, quantidade) VALUES (?, ?, ?)";
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
	
	public void atualizar(SolicitacaoProdutoFornecedor e) {
		try {
			String sql = "UPDATE solicitacao_Produto_Fornecedor SET quantidade = ? WHERE idProduto = ? AND idSolicitacao = ?";
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
	
	public void remove(SolicitacaoProdutoFornecedor e) {
		try {
			String sql = "DELETE FROM solicitacao_Produto_Fornecedor WHERE id_Solicitacao = ? AND idProduto = ? ";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, e.getIdSolicitacao());
			ps.setInt(2, e.getIdProduto());
			ps.execute();
			ps.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}
