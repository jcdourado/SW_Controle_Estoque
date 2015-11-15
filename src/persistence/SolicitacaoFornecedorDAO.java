package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.SolicitacaoFornecedor;

public class SolicitacaoFornecedorDAO {
	private Connection c;
	public SolicitacaoFornecedorDAO() {
		GenericDAO gen = new GenericDAO();
		try {
			c = gen.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void adicionar(SolicitacaoFornecedor e) {
		try {
			String sql = "INSERT INTO solicitacao_Fornecedor (codSolicitacao, codSolicitacao, data) VALUES (?, ?, ?)";
			PreparedStatement ps = c.prepareStatement(sql);
			java.sql.Date sd = new java.sql.Date( e.getData().getTime() );
			ps.setInt(1, e.getId());
			ps.setInt(2, e.getIdFornecedor());
			ps.setDate(3, sd);
			ps.execute();
			ps.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public void atualizar(int cod, SolicitacaoFornecedor e) {
		try {
			String sql = "UPDATE solicitacao_Fornecedor SET codFornecedor = ?, data = ? WHERE codSolicitacao = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			java.sql.Date sd = new java.sql.Date( e.getData().getTime() );
			ps.setInt(1, e.getIdFornecedor());
			ps.setDate(2, sd);
			ps.setInt(3, e.getId());
			ps.execute();
			ps.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public void remove(int cod) {
		try {
			String sql = "DELETE FROM solicitacao_Fornecedor WHERE codSolicitacao = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, cod);
			ps.execute();
			ps.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}
