package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Responsavel;

public class ResponsavelDAO {
	private Connection c;
	
	public ResponsavelDAO() {
		GenericDAO gen = new GenericDAO();
			c = gen.getConnection();
	}
	
	public void adicionar(Responsavel e) {
		try {
			String sql = "INSERT INTO responsavel (nome, telefone) VALUES (?, ?)";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, e.getNome());
			ps.setString(2, e.getTel());
			ps.execute();
			ps.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public void atualizar(Responsavel e) {
		try {
			String sql = "UPDATE responsavel " + 
				     " SET nome = ?, telefone = ? WHERE codResponsavel = ? ";
			PreparedStatement ps = c.prepareStatement( sql );
			ps.setString(1, e.getNome());
			ps.setString(2, e.getTel());
			ps.setInt(3, e.getId());
			ps.execute();
			ps.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public void remover(int cod) {
		try {
			String sql = "DELETE FROM responsavel WHERE codResponsavel = ? ";
			PreparedStatement ps = c.prepareStatement( sql );
			ps.setLong(1, cod);
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public int proximoId() throws SQLException {
		String sql = "SELECT MAX(codResponsavel) + 1 AS proximo_id FROM responsavel";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		if (rs.next()){
			return rs.getInt("proximo_id");
		} else {
			return 1;
		}
	}
	private String getSql(Responsavel d){
		int ver = 0;
		String sql = "SELECT codResponsavel ,nome, telefone FROM responsavel ";
		if(d.getId() != 0 ){
			sql += "WHERE codResponsavel LIKE '%" +d.getId()+"%' ";
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
		if(d.getTel() != null){
			if(ver>0){
				sql += "AND telefone LIKE '%"+d.getTel()+"%' ";
			}
			else{
				sql += "WHERE telefone LIKE '%"+d.getTel()+"%' ";	
				ver++;
			}
		}
		return sql;
	}
		
	public List<Responsavel> cons(Responsavel d) throws SQLException {
		List<Responsavel> lista = new ArrayList<Responsavel>();
		PreparedStatement ps = c.prepareStatement(getSql(d));
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Responsavel dp = new Responsavel();
			dp.setId(rs.getInt("codResponsavel"));
			dp.setNome	(rs.getString("nome"));
			dp.setTel(rs.getString("telefone"));
			lista.add(dp);
		}
		rs.close();
		ps.close();
		return lista;
	}	
}