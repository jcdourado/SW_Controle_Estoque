package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Saida;

public class SaidaDAO {
	private Connection c;
	
	public SaidaDAO() {
		GenericDAO gen = new GenericDAO();
			c = gen.getConnection();
	}
	
	public void adicionar(Saida e) {
		try {
			String sql = "INSERT INTO saida (data, descricao) VALUES (?, ?)";
			PreparedStatement ps = c.prepareStatement(sql);
			java.sql.Date sd = new java.sql.Date( e.getData().getTime() );
			ps.setDate(1, sd);
			ps.setString(2, e.getDescricao());
			ps.execute();
			ps.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
	}

	public void atualizar( Saida e) {
		try {
			String sql = "UPDATE saida " + 
				     " SET data = ?, descricao = ? " + 
				     " WHERE codSaida = ? ";
			PreparedStatement ps = c.prepareStatement( sql );
			java.sql.Date sd = new java.sql.Date( e.getData().getTime() );
			ps.setInt(3, e.getIdSaida());
			ps.setDate(1, sd);
			ps.setString(2, e.getDescricao());
			ps.execute();
			ps.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public void remover(int cod) {
		try {
			String sql = "DELETE FROM saida WHERE codSaida = ? ";
			PreparedStatement ps = c.prepareStatement( sql );
			ps.setLong(1, cod);
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public int proximoId() throws SQLException {
		String sql = "SELECT MAX(codSaida) + 1 AS proximo_id FROM saida";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		if (rs.next()){
			return rs.getInt("proximo_id");
		} else {
			return 1;
		}
	}
	private String getSql(Saida d){
		int ver = 0;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT codSaida ,data, descricao FROM saida ");
		if(d.getIdSaida() != 0 ){
			sql.append("WHERE codSaida LIKE '%" +d.getIdSaida()+"%' ");
			ver++;
		}		
		if(d.getData() != null){
			java.sql.Date sd = new java.sql.Date( d.getData().getTime() );			
			if(ver>0){
				sql.append("AND data = '%"+sd+"%' ");
			}
			else{
				sql.append("WHERE data = '%"+sd+"%' ");	
				ver++;
			}
		}
		if(d.getDescricao() != null){
			if(ver>0){
				sql.append("AND descricao LIKE '%"+d.getDescricao()+"%' ");
			}
			else{
				sql.append("WHERE descricao LIKE '%"+d.getDescricao()+"%' ");	
				ver++;
			}
		}
		return sql.toString();
	}
		
	public List<Saida> cons(Saida d) throws SQLException {
		List<Saida> lista = new ArrayList<Saida>();
		PreparedStatement ps = c.prepareStatement(getSql(d));
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Saida dp = new Saida();
			dp.setIdSaida(rs.getInt("codSaida"));
			dp.setData(rs.getDate("data"));
			dp.setDescricao(rs.getString("descricao"));
			lista.add(dp);
		}
		rs.close();
		ps.close();
		return lista;
	}	
}
