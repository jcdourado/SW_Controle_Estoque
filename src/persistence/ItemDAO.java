package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Item;
import utilities.EstoqueException;

public class ItemDAO {
private Connection c;
	
	public ItemDAO() throws EstoqueException {
		GenericDAO gen = new GenericDAO();
			c = gen.getConnection();
	}
	
	public void adicionar(Item e) throws EstoqueException {
		try {
			String sql = "INSERT INTO item (codProduto, codSaida, codEntrada) VALUES (?, ?, ?)";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, e.getIdProduto());
			ps.setInt(2, e.getIdSaida());
			ps.setInt(3, e.getIdEntrada());
			ps.execute();
			ps.close();
		} catch (SQLException e1) {
			throw new EstoqueException(e1);
		}
	}
	
	public void atualizar(Item e) throws EstoqueException {
		try {
			String sql = "UPDATE item SET codProduto = ?, codSaida = ?, codEntrada = ? WHERE codItem = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, e.getIdProduto());
			ps.setInt(2, e.getIdSaida());
			ps.setInt(3, e.getIdEntrada());
			ps.setInt(4, e.getIdItem());
			ps.execute();
			ps.close();
		} catch (SQLException e1) {
			throw new EstoqueException(e1);
		}
	}
	
	public void remove(int cod) throws EstoqueException {
		try {
			String sql = "DELETE FROM item WHERE codItem = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, cod);
			ps.execute();
			ps.close();
		} catch (SQLException e1) {
			throw new EstoqueException(e1);
		}
	}
	public int proximoId() throws EstoqueException{
		String sql = "SELECT MAX(codItem) + 1 AS proximo_id FROM item";
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
	
	private String getSql(Item d){
		int ver = 0;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT codItem ,codProduto, codSaida, codEntrada FROM item ");
		if(d.getIdEntrada() != 0 ){
			sql.append("WHERE codEntrada LIKE '%" +d.getIdEntrada()+"%' ");
			ver++;
		}		
		if(d.getIdSaida() != 0){
			if(ver>0){
				sql.append("AND codSaida LIKE '%"+d.getIdSaida()+"%' ");
			}
			else{
				sql.append("WHERE codSaida LIKE '%"+d.getIdSaida()+"%' ");	
				ver++;
			}
		}
		if(d.getIdProduto() != 0){
			if(ver>0){
				sql.append("AND codProduto LIKE '%"+d.getIdProduto()+"%' ");
			}
			else{
				sql.append("WHERE codProduto LIKE '%"+d.getIdProduto()+"%' ");	
				ver++;
			}
		}
		if(d.getIdItem() != 0){
			if(ver>0){
				sql.append("AND codItem LIKE '%"+d.getIdItem()+"%' ");
			}
			else{
				sql.append("WHERE codProduto LIKE '%"+d.getIdItem()+"%' ");	
				ver++;
			}
		}
		return sql.toString();
	}
		
	public List<Item> cons(Item d) throws EstoqueException  {
		List<Item> lista = new ArrayList<Item>();
		PreparedStatement ps;
		try {
			ps = c.prepareStatement(getSql(d));
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Item dp = new Item();
				dp.setIdEntrada(rs.getInt("codEntrada"));
				dp.setIdSaida(rs.getInt("codSaida"));
				dp.setIdProduto(rs.getInt("codProduto"));
				dp.setIdItem(rs.getInt("codItem"));
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
