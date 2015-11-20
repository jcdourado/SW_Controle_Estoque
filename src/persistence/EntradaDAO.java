package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Entrada;

public class EntradaDAO {
private Connection c;
	
	public EntradaDAO() {
		GenericDAO gen = new GenericDAO();
		c = gen.getConnection();
	}
	
	public void adicionar(Entrada e) {
		try {
			String sql = "INSERT INTO entrada (data, tipoTransferencia, NFE, "
					+ "dataEmissaoNFE, tempo, codFornecedor) VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = c.prepareStatement(sql);
			java.sql.Date sd = new java.sql.Date( e.getData().getTime() );
			java.sql.Date sd2 = new java.sql.Date( e.getDataEmissarNFE().getTime());
			ps.setDate(1, sd);
			ps.setString(2, e.getTipoTransf());
			ps.setString(3, e.getNFE());
			ps.setDate(4, sd2);
			ps.setFloat(5, e.getTempo());
			ps.setInt(6, e.getIdFornecedor());
			ps.execute();
			ps.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public void atualizar(Entrada e) {
		try {
			String sql = "UPDATE entrada " + 
				     " SET data = ?, tipoTransferencia = ?, NFE = ?, dataEmissaoNFE = ?"
				     + ", tempo = ?, codFornecedor = ? WHERE codEntrada = ? ";
			PreparedStatement ps = c.prepareStatement( sql );
			java.sql.Date sd = new java.sql.Date( e.getData().getTime() );
			java.sql.Date sd2 = new java.sql.Date( e.getDataEmissarNFE().getTime());
			ps.setDate(1, sd);
			ps.setString(2, e.getTipoTransf());
			ps.setString(3, e.getNFE());
			ps.setDate(4, sd2);
			ps.setFloat(5, e.getTempo());
			ps.setInt(6, e.getIdFornecedor());
			ps.setInt(7, e.getIdEntrada());
			ps.execute();
			ps.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public void remover(int cod) {
		try {
			String sql = "DELETE FROM entrada WHERE codEntrada = ? ";
			PreparedStatement ps = c.prepareStatement( sql );
			ps.setLong(1, cod);
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public int proximoId() throws SQLException {
		String sql = "SELECT MAX(codEntrada) + 1 AS proximo_id FROM entrada";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		if (rs.next()){
			return rs.getInt("proximo_id");
		} else {
			return 1;
		}
	}
	
	private String getSql(Entrada d){
		int ver = 0;
		String sql = "SELECT codEntrada, data, tipoTransferencia, NFE, "
					+ "dataEmissaoNFE, tempo, codFornecedor FROM entrada ";
		if(d.getIdEntrada() != 0 ){
			sql += "WHERE codEntrada LIKE '%" +d.getIdEntrada()+"%' ";
			ver++;
		}		
		if(d.getData() != null){
			java.sql.Date sd = new java.sql.Date( d.getData().getTime() );			
			if(ver>0){
				sql += "AND data = '"+sd+"' ";
			}
			else{
				sql += "WHERE data = '"+sd+"' ";	
				ver++;
			}
		}		
		if(d.getTipoTransf() != null){
			if(ver>0){
				sql += "AND tipoTransferencia LIKE '%"+d.getTipoTransf()+"%' ";
			}
			else{
				sql += "WHERE tipoTransferencia LIKE '%"+d.getTipoTransf()+"%' ";	
				ver++;
			}
		}
		if(d.getNFE() != null){
			if(ver>0){
				sql += "AND NFE LIKE '%"+d.getNFE()+"%' ";
			}
			else{
				sql += "WHERE NFE LIKE '%"+d.getNFE()+"%' ";	
				ver++;
			}
		}
		if(d.getDataEmissarNFE() != null){
			java.sql.Date sd = new java.sql.Date( d.getDataEmissarNFE().getTime() );
			if(ver>0){
				sql += "AND dataEmissaoNFE LIKE '%"+sd+"%' ";
			}
			else{
				sql += "WHERE dataEmissaoNFE LIKE '%"+sd+"%' ";	
				ver++;
			}
		}
		if(d.getTempo() != 0){
			if(ver>0){
				sql += "AND tempo LIKE '%"+d.getTempo()+"%' ";
			}
			else{
				sql += "WHERE tempo LIKE '%"+d.getTempo()+"%' ";	
				ver++;
			}
		}
		if(d.getIdFornecedor() != 0){
			if(ver>0){
				sql += "AND codFornecedor LIKE '%"+d.getIdFornecedor()+"%' ";
			}
			else{
				sql += "WHERE codFornecedor LIKE '%"+d.getIdFornecedor()+"%' ";	
				ver++;
			}
		}
		return sql;
	}
		
	public List<Entrada> cons(Entrada d) throws SQLException {
		List<Entrada> lista = new ArrayList<Entrada>();
		PreparedStatement ps = c.prepareStatement(getSql(d));
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Entrada dp = new Entrada();
			dp.setIdEntrada(rs.getInt("codEntrada"));
			dp.setData(rs.getDate("data"));
			dp.setTipoTransf(rs.getString("tipoTransferencia"));
			dp.setNFE(rs.getString("NFE"));
			dp.setDataEmissarNFE(rs.getDate("dataEmissaoNFE"));
			dp.setTempo(rs.getFloat("tempo"));
			dp.setIdFornecedor(rs.getInt("codFornecedor"));
			lista.add(dp);
		}
		rs.close();
		ps.close();
		return lista;
	}	
}
