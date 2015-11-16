package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.ProdutoSolicitacaoSaida;

public class ProSolSaiDAO {
	private Connection c;
	
	public ProSolSaiDAO() {
		GenericDAO gen = new GenericDAO();
		try {
			c = gen.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void adicionar(ProdutoSolicitacaoSaida e) {
		try {
			String sql = "INSERT INTO produto_Solicitacao_Saida (codSolicitacaoSaida, quantidade, uso, "
					+ "idPoduto, idSaida, idSolicitacao) VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, e.getCodSolicitacaoSaida());
			ps.setFloat(2, e.getQuantidade());
			ps.setString(3, e.getUso());
			ps.setInt(4, e.getIdProduto());
			ps.setInt(5, e.getIdSaida());
			ps.setInt(6, e.getIdSolicitacao());
			ps.execute();
			ps.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public void atualizar(int cod, ProdutoSolicitacaoSaida e) {
		try {
			String sql = "UPDATE produto_Solicitacao_Saida SET quantidade = ?, uso = ?, idProduto = ?, "
					+ "idEntrada = ?, idSolicitacao = ? WHERE codSolicitacaoSaida = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setFloat(1, e.getQuantidade());
			ps.setString(2, e.getUso());
			ps.setInt(3, e.getIdProduto());
			ps.setInt(4, e.getIdSaida());
			ps.setInt(5, e.getIdSolicitacao());
			ps.setInt(6, cod);
			ps.execute();
			ps.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public void remove(int cod) {
		try {
			String sql = "DELETE FROM produto_Solicitacao_Saida WHERE codcodSolicitacaoSaida = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, cod);
			ps.execute();
			ps.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}