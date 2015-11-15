package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.ProdutoSolicitacaoEntrada;

public class ProSolEntDAO {
	private Connection c;
	
	public ProSolEntDAO() {
		GenericDAO gen = new GenericDAO();
		try {
			c = gen.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void adicionar(ProdutoSolicitacaoEntrada e) {
		try {
			String sql = "INSERT INTO produto_Solicitacao_Entrada (codSolicitacaoEntrada, quantidade, uso, "
					+ "idPoduto, idEntrada, idSolicitacao) VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, e.getCodSolicitacaoEntrada());
			ps.setFloat(2, e.getQuantidade());
			ps.setString(3, e.getUso());
			ps.setInt(4, e.getIdProduto());
			ps.setInt(5, e.getIdEntrada());
			ps.setInt(6, e.getIdSolicitacao());
			ps.execute();
			ps.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public void atualizar(int cod, ProdutoSolicitacaoEntrada e) {
		try {
			String sql = "UPDATE produto_Solicitacao_Entrada SET quantidade = ?, uso = ?, idProduto = ?, "
					+ "idEntrada = ?, idSolicitacao = ? WHERE codSolicitacaoEntrada = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setFloat(1, e.getQuantidade());
			ps.setString(2, e.getUso());
			ps.setInt(3, e.getIdProduto());
			ps.setInt(4, e.getIdEntrada());
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
			String sql = "DELETE FROM produto_Solicitacao_Entrada WHERE codcodSolicitacaoEntrada = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, cod);
			ps.execute();
			ps.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}
