package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Produto;

public class ProdutoDAO {
	private Connection c;
	
	public ProdutoDAO() {
		GenericDAO gen = new GenericDAO();
		try {
			c = gen.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void adicionar(Produto e) {
		try {
			String sql = "INSERT INTO produto (nome, uso, qtdMinima, qtdSeguranca, qtdMaxima, "
					+ "consumoPrevisto, preco, peso, codTipo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, e.getNome());
			ps.setString(2, e.getUso());
			ps.setFloat(3, e.getQtdMinima());
			ps.setFloat(4, e.getQtdSeguranca());
			ps.setFloat(5, e.getQtdMaxima());
			ps.setString(6, e.getConsumoPrevisto());
			ps.setFloat(7, e.getPreco());
			ps.setFloat(8, e.getPeso());
			ps.setInt(9, e.getTipo());
			ps.execute();
			ps.close();
			} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	public void atualizar( Produto e) {
		try {
			String sql = "UPDATE produto SET nome = ?, uso = ?, qtdMinima = ?, qtdSeguranca = ?, qtdMaxima = ?, "
					+ "consumoPrevisto = ?, preco = ?, peso = ?, codTipo = ? WHERE codProduto = ? ";
			PreparedStatement ps = c.prepareStatement( sql );
			ps.setString(1, e.getNome());
			ps.setString(2, e.getUso());
			ps.setFloat(3, e.getQtdMinima());
			ps.setFloat(4, e.getQtdSeguranca());
			ps.setFloat(5, e.getQtdMaxima());
			ps.setString(6, e.getConsumoPrevisto());
			ps.setFloat(7, e.getPreco());
			ps.setFloat(8, e.getPeso());
			ps.setInt(9, e.getTipo());
			ps.setInt(10, e.getId());
			ps.execute();
			ps.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	public void remover(int cod) {
		try {
			String sql = "DELETE FROM produto WHERE codProduto = ? ";
			PreparedStatement ps = c.prepareStatement( sql );
			ps.setLong(1, cod);
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
