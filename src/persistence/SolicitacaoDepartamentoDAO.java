package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.SolicitacaoDepartamento;

public class SolicitacaoDepartamentoDAO {
	private Connection c;
	public SolicitacaoDepartamentoDAO() {
		GenericDAO gen = new GenericDAO();
			c = gen.getConnection();
	}
	
	public void adicionar(SolicitacaoDepartamento e) {
		try {
			String sql = "INSERT INTO solicitacao_Departamento (codDepartamento, data) VALUES (?, ?)";
			PreparedStatement ps = c.prepareStatement(sql);
			java.sql.Date sd = new java.sql.Date( e.getData().getTime() );
			ps.setInt(1, e.getIdDepartamento());
			ps.setDate(2, sd);
			ps.execute();
			ps.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public void atualizar(SolicitacaoDepartamento e) {
		try {
			String sql = "UPDATE solicitacao_Departamento SET codDepartamento = ?, data = ? WHERE codSolicitacao = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			java.sql.Date sd = new java.sql.Date( e.getData().getTime() );
			ps.setInt(1, e.getIdDepartamento());
			ps.setDate(2, sd);
			ps.setInt(3, e.getId());
			ps.execute();
			ps.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public void remove(int cod) {
		try {
			String sql = "DELETE FROM solicitacao_Departamento WHERE codSolicitacao = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, cod);
			ps.execute();
			ps.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	public int proximoId() throws SQLException {
		String sql = "SELECT MAX(codSolicitacao) + 1 AS proximo_id FROM solicitacao_Departamento";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		if (rs.next()){
			return rs.getInt("proximo_id");
		} else {
			return 1;
		}
	}	
	private String getSql(SolicitacaoDepartamento d){
		int ver = 0;
		String sql = "SELECT codSolicitacao ,data, codDepartamento FROM solicitacao_Departamento ";
		if(d.getId() != 0 ){
			sql += "WHERE codSolicitacao LIKE '%" +d.getId()+"%' ";
			ver++;
		}		
		if(d.getData() != null){
			java.sql.Date sd = new java.sql.Date( d.getData().getTime() );
			if(ver>0){
				sql += "AND data = '"+sd+"' ";
			}
			else{
				sql += "WHERE data = '%"+sd+"' ";	
				ver++;
			}
		}
		if(d.getIdDepartamento() != 0){
			if(ver>0){
				sql += "AND codDepartamento LIKE '%"+d.getIdDepartamento()+"%' ";
			}
			else{
				sql += "WHERE codDepartamento LIKE '%"+d.getIdDepartamento()+"%' ";	
				ver++;
			}
		}
		return sql;
	}
		
	public List<SolicitacaoDepartamento> cons(SolicitacaoDepartamento d) throws SQLException {
		List<SolicitacaoDepartamento> lista = new ArrayList<SolicitacaoDepartamento>();
		PreparedStatement ps = c.prepareStatement(getSql(d));
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			SolicitacaoDepartamento dp = new SolicitacaoDepartamento();
			dp.setId(rs.getInt("codSolicitacao"));
			dp.setData(rs.getDate("data"));
			dp.setIdDepartamento(rs.getInt("codDepartamento"));
			lista.add(dp);
		}
		rs.close();
		ps.close();
		return lista;
	}
}
