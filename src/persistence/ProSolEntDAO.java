package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.ProdutoSolicitacaoEntrada;

public class ProSolEntDAO {
	private Connection c;
	
	public ProSolEntDAO() {
		GenericDAO gen = new GenericDAO();
			c = gen.getConnection();
	}
	
	public void adicionar(ProdutoSolicitacaoEntrada e) {
		try {
			String sql = "INSERT INTO produto_Solicitacao_Entrada (quantidade, uso, "
					+ "idPoduto, idEntrada, idSolicitacao) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setFloat(1, e.getQuantidade());
			ps.setString(2, e.getUso());
			ps.setInt(3, e.getIdProduto());
			ps.setInt(4, e.getIdEntrada());
			ps.setInt(5, e.getIdSolicitacao());
			ps.execute();
			ps.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public void atualizar(ProdutoSolicitacaoEntrada e) {
		try {
			String sql = "UPDATE produto_Solicitacao_Entrada SET quantidade = ?, uso = ?, idProduto = ?, "
					+ "idEntrada = ?, idSolicitacao = ? WHERE codSolicitacaoEntrada = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setFloat(1, e.getQuantidade());
			ps.setString(2, e.getUso());
			ps.setInt(3, e.getIdProduto());
			ps.setInt(4, e.getIdEntrada());
			ps.setInt(5, e.getIdSolicitacao());
			ps.setInt(6, e.getCodSolicitacaoEntrada());
			ps.execute();
			ps.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public void remove(int cod) {
		try {
			String sql = "DELETE FROM produto_Solicitacao_Entrada WHERE codSolicitacaoEntrada = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, cod);
			ps.execute();
			ps.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}
