package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Produto;
import utilities.DBResourceManager;
import utilities.EstoqueException;

public class ProdutoDAO {
	private Connection c;
	
	public ProdutoDAO() throws EstoqueException {
		try {
			c = DBResourceManager.getRecource().getConnection();
		} catch (ClassNotFoundException e) {
			throw new EstoqueException(e);
		} catch (SQLException e) {
			throw new EstoqueException(e);
		}
	}
	
	public void adicionar(Produto e) throws EstoqueException {
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
				throw new EstoqueException(e1);
		}
	}

	public void atualizar( Produto e) throws EstoqueException {
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
			throw new EstoqueException(e1);
		}
	}

	public void remover(int cod) throws EstoqueException {
		try {
			String sql = "DELETE FROM produto WHERE codProduto = ? ";
			PreparedStatement ps = c.prepareStatement( sql );
			ps.setLong(1, cod);
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			throw new EstoqueException(e);
		}
	}
	public int proximoId() throws EstoqueException {
		String sql = "SELECT MAX(codProduto) + 1 AS proximo_id FROM produto";
		PreparedStatement ps;
		try {
			ps = c.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()){
				return rs.getInt("proximo_id");
			} else {
				return 1;
			}
		} catch (SQLException e) {
			throw new EstoqueException(e);
		}
	}	
	private String getSql(Produto d){
		int ver = 0;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT codProduto, nome ,uso , qtdMinima , qtdSeguranca , qtdMaxima , "
					+ "consumoPrevisto, preco , peso , codTipo FROM produto ");
		if(d.getId() != 0 ){
			sql.append("WHERE codProduto LIKE '%" +d.getId()+"%' ");
			ver++;
		}		
		if(d.getNome() != null){
			if(ver>0){
				sql.append("AND nome LIKE '%"+d.getNome()+"%' ");
			}
			else{
				sql.append("WHERE nome LIKE '%"+d.getNome()+"%' ");	
				ver++;
			}
		}		
		if(d.getUso() != null){
			if(ver>0){
				sql.append("AND uso LIKE '%"+d.getUso()+"%' ");
			}
			else{
				sql.append("WHERE uso LIKE '%"+d.getUso()+"%' ");	
				ver++;
			}
		}
		if(d.getQtdMinima() != 0){
			if(ver>0){
				sql.append("AND qtdMinima LIKE '%"+d.getQtdMinima()+"%' ");
			}
			else{
				sql.append("WHERE qtdMinima LIKE '%"+d.getQtdMinima()+"%' ");	
				ver++;
			}
		}
		if(d.getQtdSeguranca() != 0){
			if(ver>0){
				sql.append("AND qtdSeguranca LIKE '%"+d.getQtdSeguranca()+"%' ");
			}
			else{
				sql.append("WHERE qtdSeguranca LIKE '%"+d.getQtdSeguranca()+"%' ");	
				ver++;
			}
		}
		if(d.getQtdMaxima() != 0){
			if(ver>0){
				sql.append("AND qtdMaxima LIKE '%"+d.getQtdMaxima()+"%' ");
			}
			else{
				sql.append("WHERE qtdMaxima LIKE '%"+d.getQtdMaxima()+"%' ");	
				ver++;
			}
		}
		if(d.getConsumoPrevisto() != null){
			if(ver>0){
				sql.append("AND consumoPrevisto LIKE '%"+d.getConsumoPrevisto()+"%' ");
			}
			else{
				sql.append("WHERE consumoPrevisto LIKE '%"+d.getConsumoPrevisto()+"%' ");	
				ver++;
			}
		}
		if(d.getPeso() != 0){
			if(ver>0){
				sql.append("AND peso LIKE '%"+d.getPeso()+"%' ");
			}
			else{
				sql.append("WHERE peso LIKE '%"+d.getPeso()+"%' ");	
				ver++;
			}
		}
		if(d.getPreco() != 0){
			if(ver>0){
				sql.append("AND preco LIKE '%"+d.getPreco()+"%' ");
			}
			else{
				sql.append("WHERE preco LIKE '%"+d.getPreco()+"%' ");	
				ver++;
			}
		}
		if(d.getTipo() != 0){
			if(ver>0){
				sql.append("AND codTipo LIKE '%"+d.getTipo()+"%' ");
			}
			else{
				sql.append("WHERE codTipo LIKE '%"+d.getTipo()+"%' ");	
				ver++;
			}
		}
		return sql.toString();
	}
		
	public List<Produto> cons(Produto d) throws EstoqueException  {
		List<Produto> lista = new ArrayList<Produto>();
		PreparedStatement ps;
		try {
			ps = c.prepareStatement(getSql(d));
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
		} catch (SQLException e) {
			throw new EstoqueException(e);
		}
	}	
}
