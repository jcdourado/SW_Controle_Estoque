package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Produto;

public class ProdutoDAO {
	private Connection c;
	
	public ProdutoDAO() {
		GenericDAO gen = new GenericDAO();
			c = gen.getConnection();
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
	public int proximoId() throws SQLException {
		String sql = "SELECT MAX(codProduto) + 1 AS proximo_id FROM produto";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		if (rs.next()){
			return rs.getInt("proximo_id");
		} else {
			return 1;
		}
	}	
	private String getSql(Produto d){
		int ver = 0;
		String sql = "SELECT codProduto, nome ,uso , qtdMinima , qtdSeguranca , qtdMaxima , "
					+ "consumoPrevisto, preco , peso , codTipo FROM produto ";
		if(d.getId() != 0 ){
			sql += "WHERE codProduto LIKE '%" +d.getId()+"%' ";
			ver++;
		}		
		if(d.getNome() != null){
			if(ver>0){
				sql += "AND nome LIKE '%"+d.getNome()+"%' ";
			}
			else{
				sql += "WHERE nome LIKE '%"+d.getNome()+"%' ";	
				ver++;
			}
		}		
		if(d.getUso() != null){
			if(ver>0){
				sql += "AND uso LIKE '%"+d.getUso()+"%' ";
			}
			else{
				sql += "WHERE uso LIKE '%"+d.getUso()+"%' ";	
				ver++;
			}
		}
		if(d.getQtdMinima() != 0){
			if(ver>0){
				sql += "AND qtdMinima LIKE '%"+d.getQtdMinima()+"%' ";
			}
			else{
				sql += "WHERE qtdMinima LIKE '%"+d.getQtdMinima()+"%' ";	
				ver++;
			}
		}
		if(d.getQtdSeguranca() != 0){
			if(ver>0){
				sql += "AND qtdSeguranca LIKE '%"+d.getQtdSeguranca()+"%' ";
			}
			else{
				sql += "WHERE qtdSeguranca LIKE '%"+d.getQtdSeguranca()+"%' ";	
				ver++;
			}
		}
		if(d.getQtdMaxima() != 0){
			if(ver>0){
				sql += "AND qtdMaxima LIKE '%"+d.getQtdMaxima()+"%' ";
			}
			else{
				sql += "WHERE qtdMaxima LIKE '%"+d.getQtdMaxima()+"%' ";	
				ver++;
			}
		}
		if(d.getConsumoPrevisto() != null){
			if(ver>0){
				sql += "AND consumoPrevisto LIKE '%"+d.getConsumoPrevisto()+"%' ";
			}
			else{
				sql += "WHERE consumoPrevisto LIKE '%"+d.getConsumoPrevisto()+"%' ";	
				ver++;
			}
		}
		if(d.getPeso() != 0){
			if(ver>0){
				sql += "AND peso LIKE '%"+d.getPeso()+"%' ";
			}
			else{
				sql += "WHERE peso LIKE '%"+d.getPeso()+"%' ";	
				ver++;
			}
		}
		if(d.getPreco() != 0){
			if(ver>0){
				sql += "AND preco LIKE '%"+d.getPreco()+"%' ";
			}
			else{
				sql += "WHERE preco LIKE '%"+d.getPreco()+"%' ";	
				ver++;
			}
		}
		if(d.getTipo() != 0){
			if(ver>0){
				sql += "AND codTipo LIKE '%"+d.getTipo()+"%' ";
			}
			else{
				sql += "WHERE codTipo LIKE '%"+d.getTipo()+"%' ";	
				ver++;
			}
		}
		return sql;
	}
		
	public List<Produto> cons(Produto d) throws SQLException {
		List<Produto> lista = new ArrayList<Produto>();
		PreparedStatement ps = c.prepareStatement(getSql(d));
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Produto dp = new Produto();
			dp.setId(rs.getInt("codProduto"));
			dp.setNome(rs.getString("nome"));
			dp.setUso(rs.getString("uso"));
			dp.setQtdMinima(rs.getFloat("qtdMinima"));
			dp.setQtdMaxima(rs.getFloat("qtdMaxima"));
			dp.setQtdSeguranca(rs.getFloat("qtdSeguranca"));
			dp.setConsumoPrevisto(rs.getString("consumoPrevisto"));
			dp.setQtdSeguranca(rs.getFloat("qtdSeguranca"));
			dp.setConsumoPrevisto(rs.getString("consumoPrevisto"));
			dp.setPreco(rs.getFloat("preco"));
			dp.setPeso(rs.getFloat("peso"));
			dp.setTipo(rs.getInt("codTipo"));
			lista.add(dp);
		}
		rs.close();
		ps.close();
		return lista;
	}	
}
