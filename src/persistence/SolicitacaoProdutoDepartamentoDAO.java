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
	
	public void atualizar(int cod, SolicitacaoProdutoDepartamento e) {
		try {
			String sql = "UPDATE solicitacao_Produto_Departamento SET id_Solicitacao = ?, quantidade = ? WHERE id_Produto = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, e.getIdSolicitacao());
			ps.setFloat(2, e.getQuantidade());
			ps.setInt(3, cod);
			ps.execute();
			ps.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public void remove(int cod) {
		try {
			String sql = "DELETE FROM solicitacao_Produto_Departamento WHERE id_Produto = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, cod);
			ps.execute();
			ps.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}
